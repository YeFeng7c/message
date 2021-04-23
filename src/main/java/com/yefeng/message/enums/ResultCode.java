package com.yefeng.message.enums;

public enum ResultCode implements IFResult {
    USER_NAME_NULL(4001, "套你猴子的，把名字填上"),
    USER_MESSAGE_INFO(4002, "不填内容怎么留言"),
    USER_MESSAGE_SUCCESS(4000, "留言成功, easy peasy lemon squeeze"),
    MESSAGE_FIND_ALL(4003, "获取留言列表成功"),
    USER_MESSAGE_FAILED(4004,"不知道啥原因，留言失败"),
    REPLY_NAME(4100,"回复也要填名字"),
    REPLY_INFO(4101,"补全内容"),
    REPLY_SUCCESS(4102,"回复成功"),
    REPLY_FILED(4103,"莫名其妙就回复失败"),
    USER_NAME_NOT_NULL(4201,"姓名不能为空"),
    USER_EMAIL_NOT_NULL(4202,"邮箱不能为空"),
    USER_EMAIL_ERROR(4206,"邮箱格式不正确"),
    USER_EMAIL_EXIST(4207,"该邮箱已经注册"),
    USER_PASSWORD_NOT_NULL(4203,"密码不能为空"),
    USER_EMAIL_SEND_SUCCESS(4206,"邮件发送成功"),
    USER_REGIST_SUCCESS(4204,"注册成功"),
    USER_CODE_NULL(4028,"验证码不能为空"),
    USER__CODE_ERROR(4029,"验证码不正确"),
    USER_REGIST_FILED(4205,"邮箱或密码错误")
    ;

    private final Integer code;
    private final String message;

    private ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
