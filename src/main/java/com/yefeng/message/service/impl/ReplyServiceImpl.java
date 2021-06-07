package com.yefeng.message.service.impl;

import com.yefeng.message.mapper.ReplyMapper;
import com.yefeng.message.model.Reply;
import com.yefeng.message.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyMapper replyMapper;

    @Override
    public boolean insertReplyMessage(String id, String message_id,
                                      String reply_name, String reply_info, String reply_date) {
        replyMapper.insertReplyMessage(id,message_id,reply_name,reply_info,reply_date);
        return true;
    }

    @Override
    public List<Reply> findReplyMessage(Reply reply) {
        return replyMapper.findReplyMessage(reply);
    }
}
