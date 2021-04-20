package com.yefeng.message.model;

import lombok.Data;

@Data
public class MessageModel {
    private Long id;
    private String message_name;
    private String message_info;
    private String message_time;
}