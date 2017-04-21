package com.neuq.info.web;

import com.neuq.info.dao.CommentDao;
import com.neuq.info.dto.ResultModel;
import com.neuq.info.service.CommentService;
import com.neuq.info.service.impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import static javafx.scene.input.KeyCode.R;

/**
 * Creaed by lihang on 2017/4/21.
 */
@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @RequestMapping(value ="/{postid}/{userid}",method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ResultModel addComment(@PathVariable("postid") long postid, @PathVariable("userid") long userid, HttpServletRequest request){
        String content=request.getParameter("content");
        return commentService.addComment(content,userid,postid);
    }

    @RequestMapping(value ="/{postid}",method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ResultModel list(@PathVariable("postid") long postid){
        return commentService.queryComment(postid);
    }
}
