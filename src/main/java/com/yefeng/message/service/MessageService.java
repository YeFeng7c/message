package com.yefeng.message.service;

import com.yefeng.message.model.MessageModel;
import java.util.List;

public interface MessageService {

    boolean saveMessage(String message_name, String message_info, String message_time);

    List<MessageModel> findAllMessage(MessageModel messageModel);
}
