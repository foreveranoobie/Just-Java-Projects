package com.zhlob.auto.controller;

import com.zhlob.auto.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @Autowired
    private ArticleService articleService;

    @RequestMapping({"/", "/home"})
    public String getHomePage(Model model) {
        model.addAttribute("serverTime", System.currentTimeMillis());
        return "home";
    }
}
