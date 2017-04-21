package com.neuq.info.service.impl;

import com.neuq.info.dao.CommentDao;
import com.neuq.info.dto.ResultModel;
import com.neuq.info.entity.Comment;
import com.neuq.info.enums.ResultStatus;
import com.neuq.info.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lihang on 2017/4/21.
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao commentDao;
    public ResultModel queryComment(long postid) {
        List<Comment> list=commentDao.queryCommentByPostid(postid);
        if(list.size()==0){
            return new ResultModel(ResultStatus.FAILURE);
        }
        return new ResultModel(ResultStatus.SUCCESS, list);
    }

    public ResultModel addComment(String content, long userid, long postid) {
        Comment comment =new Comment(postid,userid,content);
        int result=commentDao.insertComment(comment);
        if(result==0){
            return new ResultModel(ResultStatus.FAILURE);
        }else {
            return new ResultModel(ResultStatus.SUCCESS);
        }

    }
}
