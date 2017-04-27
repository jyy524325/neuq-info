package com.neuq.info.web;

import com.neuq.info.dto.ResultModel;
import com.neuq.info.enums.ResultStatus;
import com.neuq.info.exception.LikeException;
import com.neuq.info.exception.RepeatLikeException;
import com.neuq.info.exception.RepeatUnLikeException;
import com.neuq.info.service.PostService;
import io.swagger.annotations.*;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.List;

import static javafx.scene.input.KeyCode.R;

/**
 * Created by lihang on 2017/4/8.
 */
@Controller
@RequestMapping("/post")
@Api(value = "post相关API",description = "post相关API")
public class PostController {
    @Autowired
    private PostService postService;

    @RequestMapping(value ="/{offset}/{limit}",method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    @ApiOperation(notes = "根据offset和limit获取post", httpMethod = "GET", value = "根据offset和limit获取post")
    @ApiImplicitParams({
                         @ApiImplicitParam(name = "offset", value = "上次返回数据的最后一个post的id", required = true,dataType = "long",paramType = "path"),
                         @ApiImplicitParam(name = "limit", value = "本次取多少条post", required = true,dataType = "int",paramType = "path"),
                         @ApiImplicitParam(name = "session", value = "登陆后返回的3rd_session", required = true,paramType = "header",dataType = "string")
                 })
    @ResponseBody
    public ResultModel list(@PathVariable("offset") int offset, @PathVariable("limit") int limit,
                           HttpServletRequest request){

        //获取列表页
        Long userId= (Long)request.getAttribute("userId");
        ResultModel resultModel=postService.queryPostByCount(offset,limit,userId);
        return resultModel;
    }

    @RequestMapping(value ="/{postId}",method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    @ApiOperation(notes = "根据postId获取post", httpMethod = "GET", value = "根据postId获取post")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "postId", value = "postId", required = true,paramType = "path",dataType = "long"),
            @ApiImplicitParam(name = "session", value = "登陆后返回的3rd_session", required = true,paramType = "header",dataType = "string")
    })
    @ResponseBody
    public ResultModel post(@PathVariable("postId") int postId,
                            HttpServletRequest request){

        //获取列表页
        Long userId= (Long)request.getAttribute("userId");
        ResultModel resultModel=postService.queryPostByPostId(postId,userId);
        return resultModel;
    }
    @ApiOperation(notes = "根据user获取post", httpMethod = "GET", value = "根据user获取post")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "session", value = "登陆后返回的3rd_session", required = true,paramType = "header",dataType = "string")
    })
    @RequestMapping(value ="/user",method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ResultModel queryByUserId(HttpServletRequest request){

        //获取列表页
        Long userId= (Long)request.getAttribute("userId");
        ResultModel resultModel=postService.queryPostByUserId(userId);
        return resultModel;
    }


    @RequestMapping(value ="/like/{postid}/{flag}",method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    @ApiOperation(notes = "点赞或取消点赞", httpMethod = "GET", value = "点赞或取消点赞")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "postid", value = "postid", required = true,dataType = "long",paramType = "path"),
            @ApiImplicitParam(name = "flag", value = "flag为1是点赞，0为取消赞", required = true,dataType = "int",paramType = "path"),
            @ApiImplicitParam(name = "session", value = "登陆后返回的3rd_session", required = true,paramType = "header",dataType = "string")
    })
    @ResponseBody
    public ResultModel like(@PathVariable("postid") long postid,@PathVariable("flag") int flag,HttpServletRequest request){
        ResultModel resultModel=null;
        Long userId= (Long)request.getAttribute("userId");
        try{
            resultModel=postService.updateLike(postid,flag,userId);
        }catch (RepeatLikeException e){
            resultModel=new ResultModel(ResultStatus.REPEAT_LIKE);
        }
        catch (RepeatUnLikeException e){
            resultModel=new ResultModel(ResultStatus.REPEAT_UNLIKE);
        }
        return resultModel;
    }
    @RequestMapping(method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ApiOperation(notes = "提交post", httpMethod = "POST", value = "提交post")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "标题", required = true,dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "content", value = "内容", required = true,dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "secret", value = "secret为1是匿名，0为非匿名", required = true,dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "session", value = "登陆后返回的3rd_session", required = true,paramType = "header",dataType = "string")
    })
    @ResponseBody
    public ResultModel add(@RequestParam("title") String title,@RequestParam("content") String content,@RequestParam("secret") int secret,
                            HttpServletRequest request){
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(title);
        //获取列表页
        Long userId= (Long)request.getAttribute("userId");
        ResultModel resultModel=postService.insertPost(title,content,secret,userId);
        return resultModel;
    }
}
