package com.yefeng.message.service.impl;

import com.yefeng.message.mapper.ArticlesKindsMapper;
import com.yefeng.message.model.ArticlesKinds;
import com.yefeng.message.service.ArticlesKindsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticlesKindsServiceImpl implements ArticlesKindsService {

    @Autowired
    private ArticlesKindsMapper articlesKindsMapper;

    @Override
    public List<ArticlesKinds> findAllArticlesKinds() {
        return articlesKindsMapper.findAllArticlesKinds();
    }
}
