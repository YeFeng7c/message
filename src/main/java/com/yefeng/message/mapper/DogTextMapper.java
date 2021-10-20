package com.yefeng.message.mapper;

import com.yefeng.message.model.DogText;

import java.util.List;

public interface DogTextMapper {

    DogText findDogTextByRandom(Long dogText_type);

    List<DogText> findDogTextType();
}
