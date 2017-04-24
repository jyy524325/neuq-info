package com.neuq.info.web;

import com.neuq.info.dto.ResultModel;
import com.neuq.info.enums.ResultStatus;
import com.neuq.info.exception.LikeException;
import com.neuq.info.exception.RepeatLikeException;
import com.neuq.info.exception.RepeatUnLikeException;
import com.neuq.info.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;

import static javafx.scene.input.KeyCode.R;

/**
 * Created by lihang on 2017/4/8.
 */
@Controller
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;

    @RequestMapping(value ="/{offset}/{limit}",method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ResultModel list(Model model, @PathVariable("offset") int offset, @PathVariable("limit") int limit,
                           HttpServletRequest request){

        //获取列表页
        Long userId= (Long)request.getAttribute("userId");
        ResultModel resultModel=postService.queryPostByCount(offset,limit,userId);
        return resultModel;
    }

    @RequestMapping(value ="/{postId}",method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ResultModel post(Model model, @PathVariable("postId") int postId,
                            HttpServletRequest request){

        //获取列表页
        Long userId= (Long)request.getAttribute("userId");
        ResultModel resultModel=postService.queryPostByPostId(postId,userId);
        return resultModel;
    }

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
    @ResponseBody
    public ResultModel like(Model model, @PathVariable("postid") long postid,@RequestParam("userId") long userId,@PathVariable("flag") int flag){
        ResultModel resultModel=null;
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

}
