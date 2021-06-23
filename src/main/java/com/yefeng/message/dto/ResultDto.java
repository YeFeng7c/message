package com.yefeng.message.dto;

import com.yefeng.message.enums.ResultCode;
import com.yefeng.message.enums.ResultException;
import lombok.Data;

@Data
public class ResultDto<T> {

    //返回的状态
    private String status;
    //返回的code值
    private Integer code;
    //返回的提示
    private String message;
    //返回的数据
    private T data;

    /**
     * 失败不带结果集
     * @param code
     * @param message
     * @param <T>
     * @return
     */
    public static <T> ResultDto<T> errorOf(Integer code, String message) {
        ResultDto<T> resultDto = new ResultDto();
        resultDto.setStatus("请求失败(4000)");
        resultDto.setCode(code);
        resultDto.setMessage(message);
        return resultDto;
    }

    /**
     * 失败带结果集
     * @param code
     * @param message
     * @param t
     * @param <T>
     * @return
     */
    public static <T> ResultDto<T> errorWithData(Integer code, String message, T t) {
        ResultDto<T> resultDto = new ResultDto();
        resultDto.setStatus("请求失败(4000)");
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

    /**
     * 成功不带结果集
     * @param code
     * @param message
     * @param <T>
     * @return
     */
    public static <T> ResultDto<T> successOf(Integer code, String message) {
        ResultDto<T> resultDto = new ResultDto();
        resultDto.setStatus("请求成功(2000)");
        resultDto.setCode(code);
        resultDto.setMessage(message);
        return resultDto;
    }

    /**
     * 成功带结果集
     * @param code
     * @param message
     * @param t
     * @param <T>
     * @return
     */
    public static <T> ResultDto<T> successWithData(Integer code, String message, T t) {
        ResultDto<T> resultDto = new ResultDto();
        resultDto.setStatus("请求成功(2000)");
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

    /**
     * 请求异常
     * @param code
     * @param message
     * @param <T>
     * @return
     */
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