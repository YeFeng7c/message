package com.yefeng.message.controller;

import com.github.pagehelper.PageHelper;
import com.yefeng.message.dto.ResultDto;
import com.yefeng.message.enums.ResultCode;
import com.yefeng.message.model.Reply;
import com.yefeng.message.service.ReplyService;
import com.yefeng.message.utils.WordFilter;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@Api(
        tags = {"留言回复接口"}
)
@RequestMapping({"/replyMessage"})
@CrossOrigin
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    @PostMapping("/insertReplyMessage")
    public ResultDto<?> insertReplyMessage(String id, String message_id,
                                           String reply_name, String reply_info, String reply_date){
        if (!StringUtils.isNotBlank(reply_name)) {
            return ResultDto.errorOf(ResultCode.REPLY_NAME);
        } else if (!StringUtils.isNotBlank(reply_info)) {
            return ResultDto.errorOf(ResultCode.REPLY_INFO);
        } else {
            Date dt = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String time = sdf.format(dt);
            String rep = WordFilter.replaceWords(reply_name);
            String reps = WordFilter.replaceWords(reply_info);
            replyService.insertReplyMessage(id, message_id, rep, reps, time);
            return ResultDto.successOf(ResultCode.REPLY_SUCCESS);
        }
    }

    @PostMapping("/findReplayMessage")
    public ResultDto<?> findReplyMessage(Reply reply){
      //  PageHelper.startPage(pageNum, pageSize);
        List<Reply> list = replyService.findReplyMessage(reply);
        return ResultDto.successWithData(ResultCode.REPLY_SUCCESS,list);
    }
}
