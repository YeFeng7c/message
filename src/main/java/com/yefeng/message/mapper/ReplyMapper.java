package com.yefeng.message.mapper;

import com.yefeng.message.model.Reply;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReplyMapper {

    void insertReplyMessage(String id, String message_id,
                            String reply_name, String reply_info, String reply_date);

    List<Reply> findReplyMessage(Reply reply);
}
