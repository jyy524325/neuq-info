package com.neuq.info.service.impl;

import com.neuq.info.dao.CommentDao;
import com.neuq.info.dao.LikeDao;
import com.neuq.info.dao.PostDao;
import com.neuq.info.dao.UserDao;
import com.neuq.info.dto.ResultModel;
import com.neuq.info.dto.UnRead;
import com.neuq.info.dto.UnReadComment;
import com.neuq.info.dto.UnReadLike;
import com.neuq.info.entity.Comment;
import com.neuq.info.entity.Like;
import com.neuq.info.entity.Post;
import com.neuq.info.entity.User;
import com.neuq.info.enums.ResultStatus;
import com.neuq.info.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.bouncycastle.asn1.x500.style.RFC4519Style.c;
import static org.bouncycastle.asn1.x500.style.RFC4519Style.seeAlso;

/**
 * Created by lihang on 2017/4/27.
 */
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private PostDao postDao;
    @Autowired
    private LikeDao likeDao;
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private UserDao userDao;

    public ResultModel getUnReadMessageCount(long userId) {
        int likeCount=likeDao.queryUnReadLikeCountByUserId(userId);
        int commentCount=commentDao.queryUnReadCountCommentByUserId(userId);
        return new ResultModel(ResultStatus.SUCCESS,  likeCount+ commentCount);
    }

    public ResultModel getUnReadMessage(long userId) {
        List<Like> likeList = likeDao.queryUnReadLikeByUserId(userId);
        List<Comment> commentList = commentDao.queryUnReadCommentByUserId(userId);
        List<UnRead> unReadList = new ArrayList<UnRead>();
        List<Long> commentIdList=new ArrayList<Long>();
        for (Like like : likeList) {
            Post post = postDao.queryPostByPostId(like.getPostId());
            unReadList.add(new UnReadLike(like, post, like.getCreateTime()));
        }
        for (Comment comment : commentList) {
            Post post = postDao.queryPostByPostId(comment.getPostId());
            unReadList.add(new UnReadComment(comment, post, comment.getCreateTime()));
        }
        Collections.sort(unReadList);

        for (Comment comment:commentList) {
            commentIdList.add(comment.getCommentId());
        }
        if(commentIdList.size()!=0){
            commentDao.updateCommentByIsRead(commentIdList);
        }
        if(likeList.size()!=0){
            likeDao.updateLikeByRead(likeList);
        }

        ResultModel resultModel;
        if (unReadList.size() != 0) {
            resultModel = new ResultModel(ResultStatus.SUCCESS, unReadList);
        } else {
            resultModel = new ResultModel(ResultStatus.NO_MORE_DATA);
        }
        return resultModel;
    }
}
