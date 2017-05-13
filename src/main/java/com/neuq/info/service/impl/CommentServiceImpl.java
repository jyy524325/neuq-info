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

    public ResultModel queryComment(long postid,long userId) {
        List<Comment> pList = commentDao.queryCommentByPostid(postid, 1, 0);
        for (int i = 0; i < pList.size(); i++) {
            List<Comment> cList = commentDao.queryCommentByPostid(postid, 2, pList.get(i).getCommentId());
            for (int j=0;j<cList.size();j++){
                if (cList.get(j).getFromUser().getUserId() == userId) {
                    cList.get(j).setIsSelf(1);
                }
            }
            pList.get(i).setcComments(cList);
            pList.get(i).setcCommentsSize(cList.size());
            pList.get(i).setFloor(i + 1);
            if (pList.get(i).getFromUser().getUserId() == userId) {
                pList.get(i).setIsSelf(1);
            }
        }

        if (pList.size() == 0) {
            return new ResultModel(ResultStatus.NO_MORE_DATA);
        }
        return new ResultModel(ResultStatus.SUCCESS, pList);
    }

    public ResultModel addComment(String content, long fromUserId, long postid, int level, long pCommentId, long toUserId) {

        Comment comment = new Comment(postid, content);
        if (level == 1) {
            Post post = postDao.queryUserIdByPostId(postid);
            toUserId = post.getUserId();
            postDao.updateCommentCount(postid,1);
        }
        int result = commentDao.insertComment(comment, fromUserId, toUserId, level, pCommentId);

        if (result == 0) {
            return new ResultModel(ResultStatus.FAILURE);
        } else {
            return new ResultModel(ResultStatus.SUCCESS);
        }

    }

    public ResultModel delComment(long commentId, long userId) {
        long fromUserid = commentDao.queryCommentUserIdByCommentId(commentId);
        if (userId != fromUserid) {
            return new ResultModel(ResultStatus.NO_PERMISSION);
        } else {
            Comment comment=commentDao.queryCommentByCommentId(commentId);
            if(comment.getLevel()==1){
                postDao.updateCommentCount(comment.getPostId(),0);
            }
            commentDao.delCComment(comment.getCommentId());
            int result = commentDao.delComment(commentId);
            if (result == 0) {
                return new ResultModel(ResultStatus.FAILURE);
            } else {
                return new ResultModel(ResultStatus.SUCCESS);
            }
        }
    }


}
