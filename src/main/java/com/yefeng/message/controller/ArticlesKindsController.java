package com.yefeng.message.controller;

import com.yefeng.message.dto.ResultDto;
import com.yefeng.message.enums.ResultCode;
import com.yefeng.message.model.ArticlesKinds;
import com.yefeng.message.service.ArticlesKindsService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(
        tags = {"文章分类接口"}
)
@RequestMapping({"/articlesKinds"})
@CrossOrigin
public class ArticlesKindsController {

    @Autowired
    private ArticlesKindsService articlesKindsService;

    @PostMapping("findAllArticlesKinds")
    public ResultDto findAllArticlesKinds() {

       List<ArticlesKinds> list = articlesKindsService.findAllArticlesKinds();

        return ResultDto.successWithData(ResultCode.ARTICLES_KINDS_SUCCESS,list);
    }
}
