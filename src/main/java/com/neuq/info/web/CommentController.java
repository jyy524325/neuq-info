package com.neuq.info.web;

import com.neuq.info.dto.ResultModel;
import com.neuq.info.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Creaed by lihang on 2017/4/21.
 */
@Controller
@RequestMapping("/comment")
@Api(value = "评论相关API")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/{postId}", method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ApiOperation(notes = "根据postId提交评论", httpMethod = "POST", value = "根据postId提交评论")

    @ApiImplicitParams({
            @ApiImplicitParam(name = "postId", value = "postId", dataType = "long", paramType = "path"),
            @ApiImplicitParam(name = "content", value = "评论内容", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "level", value = "评论类别 1：主评论 2：子评论(楼中楼评论)", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "toUserId", value = "此评论所回复的用户id", paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "pCommentId", value = "子评论的父评论id,子评论特有属性，主评论默认为0", paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "session", value = "登陆后返回的3rd_session", required = true, paramType = "header", dataType = "string")

    })
    @ResponseBody
    public ResultModel addComment(@PathVariable("postId") long postId, HttpServletRequest request,
                                  @RequestParam(required = true, value = "level") int level,
                                  @RequestParam(required = true, value = "toUserId") long toUserId,
                                  @RequestParam(required = true, value = "pCommentId") long pCommentId,
                                  @RequestParam(required = true, value = "content") String content)
    {
        Long userId = (Long) request.getAttribute("userId");

        return commentService.addComment(content, userId, postId, level, pCommentId, toUserId);
    }

    @RequestMapping(value = "/{postid}", method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    @ApiOperation(notes = "根据postId获取评论", httpMethod = "GET", value = "根据postId获取评论")

    @ApiImplicitParams({
            @ApiImplicitParam(name = "session", value = "登陆后返回的3rd_session", required = true, paramType = "header", dataType = "string")

    })
    @ResponseBody
    public ResultModel delComment(@PathVariable("postid") long postid) {
        return commentService.queryComment(postid);
    }


    @RequestMapping(value = "/delete/{commentId}", method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ApiOperation(notes = "根据postId删除评论", httpMethod = "POST", value = "根据postId删除评论")

    @ApiImplicitParams({
            @ApiImplicitParam(name = "session", value = "登陆后返回的3rd_session", required = true, paramType = "header", dataType = "string")

    })
    @ResponseBody
    public ResultModel list(@PathVariable("commentId") long commentId, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        return commentService.delComment(commentId, userId);
    }


}
