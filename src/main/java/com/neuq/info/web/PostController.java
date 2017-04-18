package com.neuq.info.web;

import com.neuq.info.dto.ResultModel;
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

    @RequestMapping(value ="/{offset}/{limit}",method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ResultModel list(Model model, @PathVariable("offset") int offset,@PathVariable("limit") int limit){
        //获取列表页
        long userId=1000;
        ResultModel resultModel=postService.queryPostByCount(offset,limit,userId);
        return resultModel;
    }
}
