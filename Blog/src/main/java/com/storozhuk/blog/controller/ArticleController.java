package com.storozhuk.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/article")
public class ArticleController {

    @GetMapping("/getForm")
    public String getArticleForm() {
        return "article/form";
    }

    @PostMapping("/saveArticle")
    public String saveArticle(@RequestParam String articleName, @RequestParam String articleText) {
        System.out.println(articleName + "\n" + articleText);
        return null;
    }
}
