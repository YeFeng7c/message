package com.yefeng.message.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yefeng.message.dto.ResultDto;
import com.yefeng.message.enums.ResultCode;
import com.yefeng.message.model.Articles;
import com.yefeng.message.model.ArticlesKinds;
import com.yefeng.message.service.ArticlesKindsService;
import com.yefeng.message.service.ArticlesService;
import com.yefeng.message.utils.PageRequest;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@Api(
        tags = {"文章接口"}
)
@RequestMapping({"/articles"})
@CrossOrigin
public class ArticlesController {

    @Autowired
    private ArticlesService articlesService;
    @Autowired
    private ArticlesKindsService articlesKindsService;

    /**
     * 分页获取所有文章列表
     *
     * @param pageRequest
     * @return
     */
    @PostMapping("/findAllArticles")
    public ResultDto<?> findAllArticles(PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<Articles> list = articlesService.findAllArticles();
        PageInfo<Articles> articlesPageInfo = new PageInfo<>(list);
        List<ArticlesKinds> articlesKinds = articlesKindsService.findAllArticlesKinds();
        Map<String, Object> map = new HashMap<>();
        map.put("articlesKinds", articlesKinds);
        map.put("articlesPageInfo", articlesPageInfo);
        return ResultDto.successWithData(ResultCode.ARTICLES_FIND_SUCCESS, map);
    }

    /**
     * 根据ID判断文章详情
     *
     * @param articles_id
     * @return
     */
    @PostMapping("/findArticlesById")
    public ResultDto<?> findArticlesById(int articles_id) {
        List<Articles> list = articlesService.findArticlesById(articles_id);
        return list.size() > 0 ? ResultDto.successWithData(ResultCode.ARTICLES_FIND_INFO_SUCCESS, list)
                : ResultDto.errorOf(ResultCode.ARTICLES_NOT_EXIST);
    }

    /**
     * 根据文章分类分页获取文章列表
     *
     * @param articles_classification
     * @return
     */
    @PostMapping("/findArticlesByKinds")
    public ResultDto<?> findArticlesByKinds(int articles_classification, PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<Articles> list = articlesService.findArticlesByKinds(articles_classification);
        PageInfo<Articles> articlesPageInfo = new PageInfo<>(list);
        return ResultDto.successWithData(ResultCode.ARTICLES_FIND_SUCCESS, articlesPageInfo);
    }
}
