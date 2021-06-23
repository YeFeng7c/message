package com.yefeng.message.vo;

import com.yefeng.message.model.User;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UserVoToUser {

    /**
     * 将表单中的对象转化为数据库中存储的用户对象（剔除表单中的code）
     * @param userVo
     * @return
     */
    public static User toUser(UserVo userVo) {

        //创建一个数据库中存储的对象
        User user = new User();

        //传值
        user.setUsername(userVo.getUsername());
        user.setPassword(userVo.getPassword());
        user.setEmail(userVo.getEmail());
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String time = sdf.format(dt);
        user.setRegist_date(time);
        // 返回包装后的对象
        return user;
    }
}