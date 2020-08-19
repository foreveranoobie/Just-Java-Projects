package com.zhlob.auto.service;

import com.zhlob.auto.domain.Article;
import com.zhlob.auto.formatter.ChainArticleFormatter;
import com.zhlob.auto.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private ChainArticleFormatter chainArticleFormatter;

    public List<Article> getArticles(int pageNumber) {
        return articleRepository.getArticles(pageNumber - 1);
    }

    public int countArticles() {
        return articleRepository.count();
    }

    @Transactional
    public Article saveArticle(String articleName, String articleText) {
        articleName = articleName;
        articleText = chainArticleFormatter.doChain(articleText);
        articleText = "<h1>" + articleName + "</h1>" + articleText;
        Date date = new Date(System.currentTimeMillis());
        Article article = new Article(articleName, "articles//" + articleName, date,
                "user", false);
        articleRepository.saveArticle(article);
        saveArticleHtml(articleName, articleText);
        return article;
    }

    public Article getById(Long id) {
        return articleRepository.getById(id);
    }

    private boolean saveArticleHtml(String articleName, String articleText) {
        File file = new File("articles//" + articleName);
        FileOutputStream outputStream = null;
        boolean isSaved = false;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file);
            outputStream.write(articleText.getBytes());
            isSaved = true;
        } catch (IOException e) {

        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            return isSaved;
        }
    }
}
