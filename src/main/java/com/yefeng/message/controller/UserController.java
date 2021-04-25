package com.yefeng.message.controller;

import com.yefeng.message.dto.ResultDto;
import com.yefeng.message.enums.ResultCode;
import com.yefeng.message.mapper.UserMapper;
import com.yefeng.message.model.User;
import com.yefeng.message.service.MailService;
import com.yefeng.message.vo.UserVo;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.regex.Pattern;

@RestController
@Api(
        tags = {"用户接口"}
)
@RequestMapping({"/user"})
@CrossOrigin
public class UserController {
    @Autowired
    private MailService mailService;

    @PostMapping("/sendEmail")
    @ResponseBody
    public ResultDto<?> sendEmail(String email, HttpServletRequest request){
        if(!StringUtils.isNotBlank(email)){
            return ResultDto.errorOf(ResultCode.USER_EMAIL_NOT_NULL);
        }
        String diffEmail = "[1-9]\\d{7,10}@qq\\.com";
        if(!Pattern.matches(diffEmail,email)){
            return ResultDto.errorOf(ResultCode.USER_EMAIL_ERROR);
        }
            mailService.sendMimeMail(email, request);
            return ResultDto.successOf(ResultCode.USER_EMAIL_SEND_SUCCESS);

    }

    @PostMapping("/regist")
    @ResponseBody
    public ResultDto<?> regist(UserVo userVo, HttpServletRequest request){
        if(!StringUtils.isNotBlank(userVo.getUsername())){
            return ResultDto.errorOf(ResultCode.USER_NAME_NOT_NULL);
        }
        if(!StringUtils.isNotBlank(userVo.getEmail())){
            return ResultDto.errorOf(ResultCode.USER_EMAIL_NOT_NULL);
        }
        String diffEmail = "[1-9]\\d{7,10}@qq\\.com";
        if(!Pattern.matches(diffEmail,userVo.getEmail())){
            return ResultDto.errorOf(ResultCode.USER_EMAIL_ERROR);
        }
        if(!StringUtils.isNotBlank(userVo.getCode())){
            return ResultDto.errorOf(ResultCode.USER_CODE_NULL);
        }
        if(!StringUtils.isNotBlank(userVo.getPassword())){
            return ResultDto.errorOf(ResultCode.USER_PASSWORD_NOT_NULL);
        }
        List<User> list = mailService.findUserByEmail(userVo.getEmail());
        if(list.size() > 0){
            return ResultDto.errorOf(ResultCode.USER_EMAIL_EXIST);
        }
        boolean success = mailService.registered(userVo,request);
        if(success) {
            return ResultDto.successOf(ResultCode.USER_REGIST_SUCCESS);
        }
        return ResultDto.errorOf(ResultCode.USER__CODE_ERROR);
    }

    @PostMapping("/login")
    @ResponseBody
    public ResultDto<?> login(String email, String password){
        if(!StringUtils.isNotBlank(email)){
            return ResultDto.errorOf(ResultCode.USER_EMAIL_NOT_NULL);
        }
        String diffEmail = "[1-9]\\d{7,10}@qq\\.com";
        if(!Pattern.matches(diffEmail,email)){
            return ResultDto.errorOf(ResultCode.USER_EMAIL_ERROR);
        }
        if(!StringUtils.isNotBlank(password)){
            return ResultDto.errorOf(ResultCode.USER_PASSWORD_NOT_NULL);
        }
        boolean success = mailService.loginIn(email,password);
        if(success){
            return ResultDto.successOf(ResultCode.USER_LOGIN_SUCCESS);
        }
        return ResultDto.successOf(ResultCode.USER_LOGIN_FILED);
    }
}