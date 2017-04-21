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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by lihang on 2017/4/8.
 */
@Controller
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;

    @RequestMapping(value ="/{offset}/{limit}/{userId}",method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ResultModel list(Model model, @PathVariable("offset") int offset,@PathVariable("limit") int limit,@PathVariable("userId") Long userId){
        //获取列表页
        ResultModel resultModel=postService.queryPostByCount(offset,limit,userId);
        return resultModel;
    }


    @RequestMapping(value ="/like/{postid}/{userid}/{flag}",method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ResultModel like(Model model, @PathVariable("postid") long postid,@PathVariable("userid") long userid,@PathVariable("flag") int flag){
        ResultModel resultModel=null;
        try{
            resultModel=postService.updateLike(postid,flag,userid);
        }catch (RepeatLikeException e){
            resultModel=new ResultModel(ResultStatus.REPEAT_LIKE);
        }
        catch (RepeatUnLikeException e){
            resultModel=new ResultModel(ResultStatus.REPEAT_UNLIKE);
        }
        return resultModel;
    }
}
