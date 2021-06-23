package com.yefeng.message.model;

import lombok.Data;

/**
 * articles_id
 * articles_title
 * articles_author
 * articles_classification
 * articles_content
 * articles_date
 * articles_read
 * articles_like
 * articles_tag
 */
@Data
public class Articles {

    private int articles_id;
    private String articles_title;
    private String articles_author;
    private int articles_classification;
    private String articles_content;
    private String articles_date;
    private int articles_read;
    private int articles_like;
    private int articles_tag;

}
