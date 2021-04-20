package com.yefeng.message.mapper;

import com.yefeng.message.model.MessageModel;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper
public interface MessageMapper {

    boolean saveMessage(String message_name, String message_info, String message_time);

    List<MessageModel> findAllMessage(MessageModel messageModel);
}
