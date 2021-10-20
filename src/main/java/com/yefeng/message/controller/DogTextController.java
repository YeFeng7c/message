package com.yefeng.message.controller;

import com.yefeng.message.dto.ResultDto;
import com.yefeng.message.enums.ResultCode;
import com.yefeng.message.model.DogText;
import com.yefeng.message.service.DogTextService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dogText")
@Api(
        tags = {"日记接口"}
)
@CrossOrigin
public class DogTextController {

    @Autowired
    private DogTextService dogTextService;

    @PostMapping("/findDogTextByRandom")
    public ResultDto<?> findDogTextByRandom(Long dogText_type){
        DogText dogText = dogTextService.findDogTextByRandom(dogText_type);
        return ResultDto.successWithData(ResultCode.DOGTEXT_FIND_RANDOM_SUCCESS,dogText);
    }

    @PostMapping("/findDogTextType")
    public ResultDto<?> findDogTextType(){
        List<DogText> list = dogTextService.findDogTextType();
        return ResultDto.successWithData(ResultCode.DOGTEXT_TYPE_FIND_SUCCESS,list);
    }
}
