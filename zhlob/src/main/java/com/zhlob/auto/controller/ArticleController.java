package com.zhlob.auto.controller;

import com.zhlob.auto.domain.Article;
import com.zhlob.auto.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Controller
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @GetMapping(value = "/createPage")
    public String getArticleCreationPage() {
        return "news//create_news";
    }

    @GetMapping("/getArticles/{page}")
    public String getArticlesList(@PathVariable(name = "page") int pageNumber, Model model) {
        model.addAttribute("articles", articleService.getArticles(pageNumber));
        return "news//articles";
    }

    @GetMapping("/getArticle/{id}")
    public String getArticle(@PathVariable Long id, Model model) throws IOException {
        Article article = articleService.getById(id);
        String contents = new String(Files.readAllBytes(Paths.get(article.getText())));
        model.addAttribute("contents", contents);
        return "news//article";
    }

    @PostMapping("/sendArticle")
    public String sendArticle(@RequestParam String articleName, @RequestParam String articleText) {
        articleService.saveArticle(articleName, articleText);
        return "redirect:/home";
    }
}
