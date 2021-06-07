package com.yefeng.message.model;

import lombok.Data;

@Data
public class Reply {

    private String id;
    private String message_id;
    private String reply_name;
    private String reply_info;
    private String reply_date;
}
