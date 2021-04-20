package com.yefeng.message.enums;

public enum ResultCode implements IFResult {
    USER_NAME_NULL(4001, "套你猴子的，把名字填上"),
    USER_MESSAGE_INFO(4002, "不填内容怎么留言"),
    USER_MESSAGE_SUCCESS(4000, "留言成功, easy peasy lemon squeeze"),
    MESSAGE_FIND_ALL(4003, "获取留言列表成功"),
    USER_MESSAGE_FAILED(4004,"不知道啥原因，留言失败")
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
