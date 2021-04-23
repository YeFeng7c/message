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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.regex.Pattern;

@Controller
@Api(
        tags = {"用户接口"}
)
@RequestMapping({"/user"})
@CrossOrigin
public class UserController {
    @Autowired
    private MailService mailService;

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/sendEmail")
    @ResponseBody
    public ResultDto<?> sendEmail(String email, HttpSession httpSession) {
        String diffEmail = "[1-9]\\d{7,10}@qq\\.com";
        if(!StringUtils.isNotBlank(email)){
            return ResultDto.errorOf(ResultCode.USER_EMAIL_NOT_NULL);
        } else if(!Pattern.matches(diffEmail,email)){
            return ResultDto.errorOf(ResultCode.USER_EMAIL_ERROR);
        } else {
            mailService.sendMimeMail(email, httpSession);
            return ResultDto.successOf(ResultCode.USER_EMAIL_SEND_SUCCESS);
        }
    }

    @PostMapping("/regist")
    @ResponseBody
    public ResultDto<?> regist(UserVo userVo, HttpSession session) {
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
        String code = (String) session.getAttribute("code");
        if(!code.equals(userVo.getCode())){
            return ResultDto.errorOf(ResultCode.USER__CODE_ERROR);
        }
        List<User> list = mailService.findUserByEmail(userVo.getEmail());
        if(list.size() > 0){
            return ResultDto.errorOf(ResultCode.USER_EMAIL_EXIST);
        }else {
            mailService.registered(userVo, session);
            return ResultDto.successOf(ResultCode.USER_REGIST_SUCCESS);
        }
    }

    @PostMapping("/login")
    @ResponseBody
    public ResultDto<?> login(String email, String password) {
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
        User user = userMapper.queryByEmail(email);
        if(!password.equals(user.getPassword())){
            return ResultDto.errorOf(ResultCode.USER_REGIST_FILED);
        }else {
            mailService.loginIn(email, password);
            return ResultDto.successOf(ResultCode.USER_REGIST_SUCCESS);
        }
    }
}