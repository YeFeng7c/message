package com.yefeng.message.controller;

import com.yefeng.message.dto.ResultDto;
import com.yefeng.message.enums.ResultCode;
import com.yefeng.message.model.LiuRen;
import com.yefeng.message.service.LiuRenService;
import io.swagger.annotations.Api;
import net.duguying.pinyin.Pinyin;
import net.duguying.pinyin.PinyinException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(
        tags = {"起卦接口"}
)
@RestController
@RequestMapping("/Divination")
@CrossOrigin
public class LiuRenController {

    @Autowired
    private LiuRenService liuRenService;

    @PostMapping("/getDivination")
    public ResultDto<?> getDivination(int month, int day, int random) {

        if (month > 12 || month <= 0){
            return ResultDto.errorOf(ResultCode.GET_LIUREN_MONTH_ERROR);
        }
        if (day > 31 || day <= 0){
            return ResultDto.errorOf(ResultCode.GET_LIUREN_DAY_ERROR);
        }
        if (random > 6 || random <= 0){
            return ResultDto.errorOf(ResultCode.GET_LIUREN_RANDOM_ERROR);
        }
        int num = Math.abs(((month + day + random) % 6) - 2);
        LiuRen liuRen = liuRenService.getLiuRen(num);
        return ResultDto.successWithData(ResultCode.GET_LIUREN_SUCCESS, liuRen);
    }

}
