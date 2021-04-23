package com.yefeng.message.controller;

import com.yefeng.message.dto.ResultDto;
import com.yefeng.message.enums.ResultCode;
import com.yefeng.message.model.MessageModel;
import com.yefeng.message.service.MessageService;
import com.yefeng.message.utils.WordFilter;
import io.swagger.annotations.Api;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(
        tags = {"留言接口"}
)
@RequestMapping({"/message"})
@CrossOrigin
public class MessageController {
    @Autowired
    private MessageService messageService;

    @PostMapping({"/info"})
    public ResultDto<?> saveMessage(@RequestParam(required = false) String message_name, @RequestParam(required = false) String message_info, String message_time) {
        if (!StringUtils.isNotBlank(message_name)) {
            return ResultDto.errorOf(ResultCode.USER_NAME_NULL);
        } else if (!StringUtils.isNotBlank(message_info)) {
            return ResultDto.errorOf(ResultCode.USER_MESSAGE_INFO);
        } else {
            Date dt = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String time = sdf.format(dt);
            String rep = WordFilter.replaceWords(message_name);
            String reps = WordFilter.replaceWords(message_info);
            boolean success = messageService.saveMessage(rep, reps, time);
            if(success){
                return ResultDto.successOf(ResultCode.USER_MESSAGE_SUCCESS);
            }
            return ResultDto.errorOf(ResultCode.USER_MESSAGE_FAILED);
        }
    }

    @PostMapping({"/findAllMessage"})
    public ResultDto<?> findAllMessage(MessageModel messageModel) {
        List<MessageModel> list = messageService.findAllMessage(messageModel);
        return ResultDto.successWithData(ResultCode.MESSAGE_FIND_ALL, list);
    }
}