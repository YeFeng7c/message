package com.yefeng.message.service;

import com.yefeng.message.model.Reply;

import java.util.List;

public interface ReplyService {

    boolean insertReplyMessage(String id, String message_id,
                               String reply_name, String reply_info, String reply_date);

    List<Reply> findReplyMessage(Reply reply);
}
