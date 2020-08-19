package com.zhlob.auto.repository;

import com.zhlob.auto.domain.Article;

import java.util.List;

public interface ArticleRepository {
    List<Article> getArticles(int pageNumber);

    int count();

    Article saveArticle(Article article);

    Article getById(Long id);
}
