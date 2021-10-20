package com.yefeng.message.service;

import com.yefeng.message.model.DogText;

import java.util.List;

public interface DogTextService {

    DogText findDogTextByRandom(Long dogText_type);

    List<DogText> findDogTextType();
}
