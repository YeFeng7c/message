package com.yefeng.message.service;

import com.yefeng.message.model.Articles;

import java.util.List;

public interface ArticlesService {

    List<Articles> findAllArticles();
    List<Articles> findArticlesById(int articles_id);
    List<Articles> findArticlesByKinds(int articles_classification);
}
