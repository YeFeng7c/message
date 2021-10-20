package com.yefeng.message.controller;

import com.yefeng.message.dto.ResultDto;
import com.yefeng.message.enums.ResultCode;
import io.swagger.annotations.Api;
import net.duguying.pinyin.Pinyin;
import net.duguying.pinyin.PinyinException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(
        tags = {"拼音转换接口"}
)
@RestController
@CrossOrigin
@RequestMapping("/Convert")
public class PinYinController {

    @PostMapping("getConvert")
    public ResultDto<?> getConvert(String text) throws PinyinException {

        Pinyin pinyin = new Pinyin();
        String translate = pinyin.translate(text);
        return ResultDto.successWithData(ResultCode.GET_PINYIN_CONVERT_SUCCESS, translate);
    }
}
