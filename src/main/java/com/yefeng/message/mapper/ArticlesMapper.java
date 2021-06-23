package com.yefeng.message.mapper;

import com.yefeng.message.model.Articles;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticlesMapper {

    List<Articles> findAllArticles ();
    List<Articles> findArticlesById(int articles_id);
    List<Articles> findArticlesByKinds(int articles_classification);
}
