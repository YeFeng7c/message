package com.yefeng.message.controller;

import com.yefeng.message.dto.ResultDto;
import com.yefeng.message.enums.ResultCode;
import io.swagger.annotations.Api;
import me.ihxq.projects.pna.PhoneNumberInfo;
import me.ihxq.projects.pna.PhoneNumberLookup;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(
        tags = {"手机号查询接口"}
)
@RestController
@CrossOrigin
@RequestMapping("/phoneNumber")
public class PhoneNumberController {

    @PostMapping("/getPhoneNumber")
    public ResultDto<?> getPhoneNumber(String number){
        PhoneNumberLookup phoneNumberLookup = new PhoneNumberLookup();
        PhoneNumberInfo found = phoneNumberLookup.lookup(number).orElseThrow(RuntimeException::new);
        return ResultDto.successWithData(ResultCode.GET_PHONE_NUMBER_SUCCESS,found);
    }
}
