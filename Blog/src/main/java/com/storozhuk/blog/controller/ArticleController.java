package com.storozhuk.blog.controller;

import com.storozhuk.blog.formatter.ChainArticleFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/previewArticle")
    @ResponseBody
    public String previewArticle(@RequestParam String articleName, @RequestParam String articleText,
                                 @Autowired ChainArticleFormatter articleFormatter) {
        articleFormatter.doChain(articleText);
        articleName = "<h1>" + articleName + "</h1>";
        return articleName + articleText;
    }
}
