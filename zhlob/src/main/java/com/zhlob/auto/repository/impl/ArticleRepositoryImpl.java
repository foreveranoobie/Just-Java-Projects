package com.zhlob.auto.repository.impl;

import com.zhlob.auto.domain.Article;
import com.zhlob.auto.repository.ArticleRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ArticleRepositoryImpl implements ArticleRepository {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Article> getArticles(int pageNumber) {
        Session session = sessionFactory.openSession();
        List<Article> articles =
                session.createQuery("FROM Article", Article.class).setFirstResult(pageNumber * 5).setMaxResults(5).list();
        session.close();
        return articles;
    }

    @Override
    public int count() {
        return 0;
    }

    @Override
    public Article saveArticle(Article article) {
        Session session = sessionFactory.openSession();
        session.save(article);
        session.close();
        return article;
    }

    @Override
    public Article getById(Long id) {
        Session session = sessionFactory.openSession();
        Article article =
                session.createQuery("from Article a where a.id=:id", Article.class).setParameter("id", id).getSingleResult();
        session.close();
        return article;
    }
}
