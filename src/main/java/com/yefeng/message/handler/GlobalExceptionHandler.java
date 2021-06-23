package com.yefeng.message.handler;
import com.yefeng.message.dto.ResultDto;
import com.yefeng.message.enums.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler  {

    @ExceptionHandler(Throwable.class)
    public ResultDto handleThrowable(Throwable e, HttpServletRequest request) {
        log.error("URL:{} ,系统异常: ",request.getRequestURI(), e);
        return ResultDto.errorOf(ResultCode.DADA_EXCEPTION);
    }

    @ExceptionHandler(BindException.class)
    public ResultDto exceptionHandler(BindException e, HttpServletRequest request) {
        String failMsg = Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage();

        log.error("URL:{} ,绑定异常:{} ", request.getRequestURI(),failMsg);
        return ResultDto.errorOf(ResultCode.DADA_EXCEPTION);
    }
}
