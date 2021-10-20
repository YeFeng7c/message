package com.yefeng.message.service.impl;

import com.yefeng.message.mapper.DogTextMapper;
import com.yefeng.message.model.DogText;
import com.yefeng.message.service.DogTextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DogTextServiceImpl implements DogTextService {

    @Autowired
    private DogTextMapper dogTextMapper;

    @Override
    public DogText findDogTextByRandom(Long dogText_type) {
        return dogTextMapper.findDogTextByRandom(dogText_type);
    }

    @Override
    public List<DogText> findDogTextType() {
        return dogTextMapper.findDogTextType();
    }
}
