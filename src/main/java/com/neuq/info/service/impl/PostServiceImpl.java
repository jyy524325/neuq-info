package com.neuq.info.service.impl;

import com.neuq.info.dao.PostDao;
import com.neuq.info.dto.Page;
import com.neuq.info.dto.ResultModel;
import com.neuq.info.entity.Post;
import com.neuq.info.enums.ResultStatus;
import com.neuq.info.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static javafx.scene.input.KeyCode.R;

/**
 * Created by lihang on 2017/4/4.
 */
@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostDao postDao;
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

    public ResultModel queryPostByCount(int offset ,int limit) {
        ResultModel resultModel;
        List<Post> list=postDao.queryPostByCount(offset,limit);

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
