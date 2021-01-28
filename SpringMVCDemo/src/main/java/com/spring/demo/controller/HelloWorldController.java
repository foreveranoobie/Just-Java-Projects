package com.spring.demo.controller;

import jakarta.servlet.http.HttpServletRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Controller
@RequestMapping("/greeting")
public class HelloWorldController {
    @RequestMapping("/showForm")
    public String getForm() {
        return "greetingForm";
    }

    @RequestMapping("/processForm")
    public String processForm() {
        return "greeting";
    }

    @RequestMapping("/processFormAdditionally")
    public String shoutGreeting(@RequestParam String userName, Model model) {
        userName = userName.toUpperCase(Locale.ROOT);
        String greeting = "Yo! " + userName;
        model.addAttribute("message", greeting);
        return "greeting";
    }
}
