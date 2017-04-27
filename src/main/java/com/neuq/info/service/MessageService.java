package com.neuq.info.service;

import com.neuq.info.dto.ResultModel;
import com.neuq.info.dto.UnRead;

import java.util.List;

/**
 * Created by lihang on 2017/4/27.
 */
public interface MessageService {
    ResultModel getUnReadMessageCount(long userId);
    ResultModel getUnReadMessage(long userId);

}
