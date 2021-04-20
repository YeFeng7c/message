package com.yefeng.message.dto;

import com.yefeng.message.enums.ResultCode;
import com.yefeng.message.enums.ResultException;
import lombok.Data;

@Data
public class ResultDto<T> {
    private String status;
    private Integer code;
    private String message;
    private T data;

    public static <T> ResultDto<T> errorOf(Integer code, String message) {
        ResultDto<T> resultDto = new ResultDto();
        resultDto.setStatus("请求失败4000");
        resultDto.setCode(code);
        resultDto.setMessage(message);
        return resultDto;
    }

    public static <T> ResultDto<T> errorWithData(Integer code, String message, T t) {
        ResultDto<T> resultDto = new ResultDto();
        resultDto.setStatus("请求失败4000");
        resultDto.setCode(code);
        resultDto.setMessage(message);
        resultDto.setData(t);
        return resultDto;
    }

    public static <T> ResultDto<T> errorOf(ResultCode errorCode) {
        return errorOf(errorCode.getCode(), errorCode.getMessage());
    }

    public static <T> ResultDto<T> errorWithData(ResultCode errorCode, T t) {
        return errorWithData(errorCode.getCode(), errorCode.getMessage(), t);
    }

    public static <T> ResultDto<T> successOf(Integer code, String message) {
        ResultDto<T> resultDto = new ResultDto();
        resultDto.setStatus("请求成功2000");
        resultDto.setCode(code);
        resultDto.setMessage(message);
        return resultDto;
    }

    public static <T> ResultDto<T> successWithData(Integer code, String message, T t) {
        ResultDto<T> resultDto = new ResultDto();
        resultDto.setStatus("请求成功2000");
        resultDto.setCode(code);
        resultDto.setMessage(message);
        resultDto.setData(t);
        return resultDto;
    }

    public static <T> ResultDto<T> successOf(ResultCode successCode) {
        return successOf(successCode.getCode(), successCode.getMessage());
    }

    public static <T> ResultDto<T> successWithData(ResultCode successCode, T t) {
        return successWithData(successCode.getCode(), successCode.getMessage(), t);
    }

    public static <T> ResultDto<T> exceptionOf(Integer code, String message) {
        ResultDto<T> resultDto = new ResultDto();
        resultDto.setStatus("请求异常(5000)");
        resultDto.setCode(code);
        resultDto.setMessage(message);
        return resultDto;
    }
    public static <T> ResultDto<T> exceptionOf(ResultException e) {
        return exceptionOf(e.getCode(), e.getMessage());
    }
}