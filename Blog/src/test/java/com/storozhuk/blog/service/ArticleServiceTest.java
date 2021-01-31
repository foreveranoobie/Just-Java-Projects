package com.storozhuk.blog.service;

import com.storozhuk.blog.entity.Article;
import com.storozhuk.blog.formatter.ChainArticleFormatter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.Assert.assertTrue;

public class ArticleServiceTest {
    public static ArticleService articleService;

    @BeforeAll
    public static void initFields() {
        articleService = new ArticleService();
    }

    @Test
    public void testOnCorrectClipboardImageSavings() {
        Article article = new Article("Hello, World!",
                "<img style='display: block; margin: 1% auto 2% auto; max-width:50%; max-height:50%;' " +
                        "src='/articles/template/image_0.jpg'/><br/>");
        articleService.saveArticle(article, new ChainArticleFormatter());
        assertTrue(new File("src/main/resources/static/articles/autozhlob/Hello, World!").exists());
        assertTrue(new File("src/main/resources/static/articles/autozhlob/template").listFiles().length == 0);
    }
}
