package com.neuq.info.service.impl;

import com.neuq.info.dao.LikeDao;
import com.neuq.info.dao.PostDao;
import com.neuq.info.dto.Page;
import com.neuq.info.dto.ResultModel;
import com.neuq.info.entity.Like;
import com.neuq.info.entity.Post;
import com.neuq.info.enums.ResultStatus;
import com.neuq.info.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static javafx.scene.input.KeyCode.R;

/**
 * Created by lihang on 2017/4/4.
 */
@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostDao postDao;
    @Autowired
    private LikeDao likeDao;
    public ResultModel insertPost(Post post) {
        ResultModel resultModel;
        int count =postDao.insertPost(post);

        if(count==0){
            resultModel=new ResultModel(ResultStatus.INSERT_FAIL,count);
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
        }
        if(list.size()==0){
            resultModel=new ResultModel(ResultStatus.INSERT_FAIL,list);
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
            resultModel=new ResultModel(ResultStatus.INSERT_FAIL,list);
        }else {
            resultModel = new ResultModel(ResultStatus.SUCCESS, list);
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
            resultModel=new ResultModel(ResultStatus.INSERT_FAIL,count);
        }else {
            resultModel = new ResultModel(ResultStatus.SUCCESS, count);
        }
        return resultModel;
    }

    public ResultModel updateLikeCount(long postId, int flag) {
        ResultModel resultModel;
        int count=postDao.updateLikeCount(postId,flag);

        if(count==0){
            resultModel=new ResultModel(ResultStatus.INSERT_FAIL,count);
        }else {
            resultModel = new ResultModel(ResultStatus.SUCCESS, count);
        }
        return resultModel;
    }

    public ResultModel updateCommentCount(long postId) {
        ResultModel resultModel;
        int count=postDao.updateCommentCount(postId);

        if(count==0){
            resultModel=new ResultModel(ResultStatus.INSERT_FAIL,count);
        }else {
            resultModel = new ResultModel(ResultStatus.SUCCESS, count);
        }
        return resultModel;
    }

}
