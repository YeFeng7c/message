package com.yefeng.message.controller;

import com.nlf.calendar.Lunar;
import com.yefeng.message.dto.ResultDto;
import com.yefeng.message.enums.ResultCode;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Api(
        tags = {"日历接口"}
)
@RequestMapping("/Lunar")
@RestController
@CrossOrigin
public class LunarController {

    @PostMapping("/getLunar")
    public ResultDto<?> getLunar(Integer lunarYear, Integer lunarMonth, Integer lunarDay) {
        if (lunarYear == null) {
            return ResultDto.errorOf(ResultCode.LUNAR_YEAR_NOT_NULL);
        } else if (lunarMonth == null) {
            return ResultDto.errorOf(ResultCode.LUNAR_MONTH_NOT_NULL);
        } else if (lunarDay == null) {
            return ResultDto.errorOf(ResultCode.LUNAR_DAY_NOT_NULL);
        } else {
            Lunar lunar = new Lunar(lunarYear, lunarMonth, lunarDay);
            String s = lunar.toFullString();
            String s1 = lunar.getSolar().toFullString();
            List list = new ArrayList<>();
            list.add(s);
            list.add(s1);
            return ResultDto.successWithData(ResultCode.LUNAR_GET_SUCCESS, list);
        }
    }
}
