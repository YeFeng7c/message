package com.yefeng.message.service;
import com.yefeng.message.mapper.UserMapper;
import com.yefeng.message.model.User;
import com.yefeng.message.vo.UserVo;
import com.yefeng.message.vo.UserVoToUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Random;

@Service
public class MailService {
    @Autowired
    private JavaMailSender mailSender;//一定要用@Autowired

    @Autowired
    private UserMapper userMapper;//注入UserMapper，交给bena

    //application.properties中已配置的值
    @Value("${spring.mail.username}")
    private String from;

    /**
     * 给前端输入的邮箱，发送验证码
     * @param email
     * @param request
     * @return
     */
    public boolean sendMimeMail( String email, HttpServletRequest request) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setSubject("验证码邮件");//主题
            //生成随机数
            String code = randomCode();
            //将随机数放置到session中
//            session.setAttribute("email",email);
//            session.setAttribute("code",code);
            request.getServletContext().setAttribute("email",email);
            request.getServletContext().setAttribute("code",code);
//            System.out.println("接受的email" + email);
//            System.out.println("存入session的email"+request.getServletContext().getAttribute("email"));
            mailMessage.setText("您收到的验证码是："+code);//内容
            mailMessage.setTo(email);//发给谁
            mailMessage.setFrom(from);//你自己的邮箱
            mailSender.send(mailMessage);//发送
            return  true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 随机生成6位数的验证码
     * @return String code
     */
    public String randomCode(){
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            str.append(random.nextInt(10));
        }
        return str.toString();
    }

    /**
     * 检验验证码是否一致
     * @param userVo
     * @param request
     * @return
     */
    public boolean registered(UserVo userVo, HttpServletRequest request){
        //获取session中的验证信息
        String email = (String) request.getServletContext().getAttribute("email");
        String code = (String) request.getServletContext().getAttribute("code");

        //获取表单中的提交的验证信息
        String voCode = userVo.getCode();
        String voEmail = userVo.getEmail();

//        System.out.println("发送的email" + email);
//        System.out.println("输入的email" + voEmail);
        //如果email数据为空，或者不一致，注册失败
        if (!StringUtils.isNotBlank(email)) {
            //return "error,请重新注册";
            return false;
        }
        else if (!code.equals(voCode)||!email.equals(voEmail)){
            //return "error,请重新注册";
            return false;
        }

        //保存数据
        User user = UserVoToUser.toUser(userVo);

        //将数据写入数据库
        userMapper.insertUser(user);

        //跳转成功页面
        return true;
    }

    /**
     * 通过输入email查询password，然后比较两个password，如果一样，登录成功
     * @param email
     * @param password
     * @return
     */

    public boolean loginIn(String email, String password){

        User user = userMapper.queryByEmail(email);

        if(!user.getPassword().equals(password)){
            return false;
        }
//        System.out.println("登录成功:数据库密码是："+user.getPassword());
        return true;
    }

    public List<User> findUserByEmail(String email){
        return userMapper.findUserByEmail(email);
    }
}