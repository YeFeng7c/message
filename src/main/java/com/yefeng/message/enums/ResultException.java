package com.yefeng.message.enums;

public class ResultException extends RuntimeException {
    Integer code;
    String message;

    public ResultException(ResultCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
