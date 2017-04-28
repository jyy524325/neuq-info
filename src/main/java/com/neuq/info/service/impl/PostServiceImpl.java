package com.neuq.info.service.impl;

import com.neuq.info.common.constant.SecretUrl;
import com.neuq.info.dao.LikeDao;
import com.neuq.info.dao.PostDao;
import com.neuq.info.dto.Page;
import com.neuq.info.dto.ResultModel;
import com.neuq.info.entity.Like;
import com.neuq.info.entity.Post;
import com.neuq.info.enums.ResultStatus;
import com.neuq.info.exception.RepeatLikeException;
import com.neuq.info.exception.RepeatUnLikeException;
import com.neuq.info.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lihang on 2017/4/4.
 */
@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostDao postDao;
    @Autowired
    private LikeDao likeDao;
    @Autowired
    private SecretUrl secretUrl;
    public ResultModel insertPost(String title,String content,int secret,long userId) {

        Post post=new Post();
        post.setSecret(secret);
        post.setContent(content);
        post.setTitle(title);
        post.setUserId(userId);
        ResultModel resultModel;
        int count =postDao.insertPost(post);

        if(count==0){
            resultModel=new ResultModel(ResultStatus.FAILURE,count);
        }else {
            resultModel = new ResultModel(ResultStatus.SUCCESS, count);
        }
        return resultModel;

    }

    public ResultModel queryPostByCount(int offset ,int limit,Long userId) {
        ResultModel resultModel;
        List<Post> list=postDao.queryPostByCount(offset,limit);
        List<Like> like= likeDao.queryUserLikeByUserId(userId);
        List<Long> postIdList=new ArrayList<Long>();
        for (Like tempLike:like){
            postIdList.add(tempLike.getPostId());
        }
        for (int i=0;i<list.size();i++){
            if(list.get(i).getUserId()==userId){
                list.get(i).setIsSelf(1);
            }
            if(postIdList.contains(list.get(i).getPostId())){
                list.get(i).setIsLike(1);
            }
            if(list.get(i).getSecret()==1){
                list.get(i).setUserId(-1);
                list.get(i).setAvatarUrl(secretUrl.getUrl().get((int)list.get(i).getPostId()%secretUrl.getUrl().size()));
                if(list.get(i).getGender()==1){
                    list.get(i).setNickName("匿名男同学");
                }else{
                    list.get(i).setNickName("匿名女同学");
                }
            }
        }
        if(list.size()==0){
            resultModel=new ResultModel(ResultStatus.NO_MORE_DATA,list);
        }else {
            resultModel = new ResultModel(ResultStatus.SUCCESS, list);
        }
        return resultModel;

    }

    public ResultModel queryPostByPage(int currentPage) {
        Page page =new Page();
        page.setCurrentPage(currentPage);
        page.setTotalNumber(postDao.queryAllPostCount());
        ResultModel resultModel;
        List<Post> list=postDao.queryPostByPage(page);

        if(list.size()==0){
            resultModel=new ResultModel(ResultStatus.FAILURE,list);
        }else {
            resultModel = new ResultModel(ResultStatus.SUCCESS, list);
        }
        return resultModel;
    }

    public ResultModel queryPostByUserId(long userId) {
        ResultModel resultModel;
        List<Post> list=postDao.queryPostByUserId(userId);
        List<Like> like= likeDao.queryUserLikeByUserId(userId);
        List<Long> postIdList=new ArrayList<Long>();
        for (Like tempLike:like){
            postIdList.add(tempLike.getPostId());
        }
        for (int i=0;i<list.size();i++){
            if(list.get(i).getUserId()==userId){
                list.get(i).setIsSelf(1);
            }
            if(postIdList.contains(list.get(i).getPostId())){
                list.get(i).setIsLike(1);
            }
        }
        if(list.size()==0){
            resultModel=new ResultModel(ResultStatus.NO_MORE_DATA,list);
        }else {
            resultModel = new ResultModel(ResultStatus.SUCCESS, list);
        }
        return resultModel;
    }

    public ResultModel queryPostByPostId(long postId,long userId) {
        ResultModel resultModel;
        Post post=postDao.queryPostByPostId(postId);
        if(post!=null){
        if(post.getUserId()==userId) post.setIsSelf(1);
        List<Like> like= likeDao.queryUserLikeByUserId(userId);
        List<Long> postIdList=new ArrayList<Long>();
        for (Like tempLike:like){
            postIdList.add(tempLike.getPostId());
        }
            if(postIdList.contains(post.getPostId())){
            post.setIsLike(1);
        }
        if(post.getSecret()==1){
            post.setUserId(-1);
            post.setAvatarUrl(secretUrl.getUrl().get((int)post.getPostId()%secretUrl.getUrl().size()));
            if(post.getGender()==1){
                post.setNickName("匿名男同学");
            }else {
                post.setNickName("匿名女同学");
            }

        }

            resultModel = new ResultModel(ResultStatus.SUCCESS, post);
        }else {
            resultModel=new ResultModel(ResultStatus.FAILURE,post);

        }
        return resultModel;
    }

    public int queryAllPostCount() {
        return postDao.queryAllPostCount();
    }

    public ResultModel deletePost(long postId) {
        ResultModel resultModel;
        int count=postDao.deletePost(postId);

        if(count==0){
            resultModel=new ResultModel(ResultStatus.NO_MORE_DATA,count);
        }else {
            resultModel = new ResultModel(ResultStatus.SUCCESS, count);
        }
        return resultModel;
    }
    @Transactional
    public ResultModel updateLike(long postId, int flag,long userId) {
        ResultModel resultModel;
        int count1=0;
        Like like=new Like(postId,userId);
        int count=postDao.updateLikeCount(postId,flag);
        System.out.println("执行更新数量操作");
        if(flag==1){
            System.out.println("执行插入操作");
            count1=likeDao.insertUserLike(like);
            if(count1==0) throw new RepeatLikeException("重复点赞");
        }
        else if(flag==0){
            System.out.println("执行删除操作");
            count1=likeDao.deleteUserLike(like);
            if(count1==0) throw new RepeatUnLikeException("重复取消赞");
        }
        resultModel = new ResultModel(ResultStatus.SUCCESS, count);
        return resultModel;
    }

    public ResultModel updateCommentCount(long postId) {
        ResultModel resultModel;
        int count=postDao.updateCommentCount(postId);

        if(count==0){
            resultModel=new ResultModel(ResultStatus.FAILURE,count);
        }else {
            resultModel = new ResultModel(ResultStatus.SUCCESS, count);
        }
        return resultModel;
    }

}
