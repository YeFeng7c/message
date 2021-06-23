package com.yefeng.message.vo;

import lombok.Data;

@Data
public class UserVo {
    private String username;

    private String password;

    private String email;

    private String regist_date;
    //    验证码
    private String code;

    //省略了get和set方法，自己生成一下
}