package com.zhlob.auto.controller;

import com.captcha.botdetect.web.servlet.Captcha;
import com.zhlob.auto.captcha.CaptchaDescriptor;
import com.zhlob.auto.domain.User;
import com.zhlob.auto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/auth")
public class AuthorizationController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String registerUser(HttpServletRequest request,
                               @ModelAttribute("captchaDescriptor") CaptchaDescriptor captchaDescriptor,
                               @ModelAttribute User user) {
        Captcha captcha = Captcha.load(request, "captchaDescriptor");
        boolean isHuman = captcha.validate(captchaDescriptor.getCaptchaCode());
        if (isHuman && userService.saveUser(user)) {
            captchaDescriptor = null;
            return "redirect:/home";
        } else {
            return "user/register";
        }
    }

    @GetMapping("/register")
    public ModelAndView getRegisterPage() {
        ModelAndView modelAndView = new ModelAndView("user/register");
        modelAndView.addObject("captchaDescriptor", new CaptchaDescriptor())
                .addObject("user", new User());
        return modelAndView;
    }
}
