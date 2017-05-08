package com.neuq.info.service.impl;

import com.neuq.info.dao.CommentDao;
import com.neuq.info.dao.PostDao;
import com.neuq.info.dto.ResultModel;
import com.neuq.info.entity.Comment;
import com.neuq.info.entity.Post;
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
    @Autowired
    private PostDao postDao;
    public ResultModel queryComment(long postid) {
        List<Comment> pList=commentDao.queryCommentByPostid(postid,1,0);

        System.out.println(pList.size());
        for (int i = 0; i <pList.size() ; i++) {
            List<Comment> cList=commentDao.queryCommentByPostid(postid,2,pList.get(i).getCommentId());
            System.out.println(cList);
            pList.get(i).setcComments(cList);
        }
        Post post =postDao.queryPostByPostId(postid);

        for (int i=0;i<pList.size();i++){
            if (pList.get(i).getFromUser().getUserId()==post.getUserId()){
                pList.get(i).setIsAuther(1);
            }
        }
        if(pList.size()==0){
            return new ResultModel(ResultStatus.NO_MORE_DATA);
        }
        return new ResultModel(ResultStatus.SUCCESS, pList);
    }

    public ResultModel addComment(String content, long fromUserId, long postid,int level,long pCommentId,long toUserId) {

        Comment comment =new Comment(postid,content);
        if(level==1){
            Post post =postDao.queryUserIdByPostId(postid);
            toUserId=post.getUserId();
        }
        int result=commentDao.insertComment(comment,fromUserId,toUserId,level,pCommentId);
        postDao.updateCommentCount(postid);
        if(result==0){
            return new ResultModel(ResultStatus.FAILURE);
        }else {
            return new ResultModel(ResultStatus.SUCCESS);
        }

    }
}
