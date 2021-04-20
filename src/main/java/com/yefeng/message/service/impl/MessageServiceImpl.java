package com.yefeng.message.service.impl;

import com.yefeng.message.mapper.MessageMapper;
import com.yefeng.message.model.MessageModel;
import com.yefeng.message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageMapper messageMapper;

    public MessageServiceImpl() {
    }

    public boolean saveMessage(String message_name, String message_info, String message_time) {
        return messageMapper.saveMessage(message_name, message_info, message_time);
    }

    public List<MessageModel> findAllMessage(MessageModel messageModel) {
        List<MessageModel> list = messageMapper.findAllMessage(messageModel);
        return list;
    }
}