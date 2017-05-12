package com.neuq.info.web;

import com.neuq.info.dto.ResultModel;
import com.neuq.info.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lihang on 2017/4/28.
 */
@Controller
@RequestMapping("/personal")
@Api(value = "个人中心相关api")
public class PersonalController {
    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "/getUnRead", method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    @ApiOperation(notes = "获取未读消息", httpMethod = "GET", value = "获取未读消息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "session", value = "登陆后返回的3rd_session", required = true, paramType = "header", dataType = "string")
    })
    @ResponseBody
    public ResultModel getUnRead(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        ResultModel resultModel = messageService.getUnReadMessage(userId);
        return resultModel;
    }

    @RequestMapping(value = "/getUnReadCount", method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    @ApiOperation(notes = "获取未读消息条数", httpMethod = "GET", value = "获取未读消息条数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "session", value = "登陆后返回的3rd_session", required = true, paramType = "header", dataType = "string")
    })
    @ResponseBody
    public ResultModel getUnReadCount(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        ResultModel resultModel = messageService.getUnReadMessageCount(userId);
        return resultModel;
    }
}
