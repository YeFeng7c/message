package com.yefeng.message.mapper;

import com.yefeng.message.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    /**
     * 注册，插入数据     * @param user
     */
    void insertUser(User user);

    /**
     * 根据邮箱查询     * @param email     * @return
     */
    User queryByEmail(String email);

    List<User> findUserByEmail(String email);
}