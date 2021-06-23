package com.yefeng.message.service.impl;

import com.yefeng.message.mapper.ArticlesMapper;
import com.yefeng.message.model.Articles;
import com.yefeng.message.service.ArticlesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticlesServiceImpl implements ArticlesService {

    @Autowired
    private ArticlesMapper articlesMapper;

    @Override
    public List<Articles> findAllArticles() {
        return articlesMapper.findAllArticles();
    }

    @Override
    public List<Articles> findArticlesById(int articles_id) {
        return articlesMapper.findArticlesById(articles_id);
    }

    @Override
    public List<Articles> findArticlesByKinds(int articles_classification) {
        return articlesMapper.findArticlesByKinds(articles_classification);
    }
}
