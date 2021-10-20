package com.yefeng.message.service.impl;

import com.yefeng.message.mapper.LiuRenMapper;
import com.yefeng.message.model.LiuRen;
import com.yefeng.message.service.LiuRenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LiuRenServiceImpl implements LiuRenService {

    @Autowired
    LiuRenMapper liuRenMapper;

    @Override
    public LiuRen getLiuRen(int id) {
        return liuRenMapper.getLiuRen(id);
    }
}
