package com.zhlob.auto.controller;

import com.captcha.botdetect.web.servlet.Captcha;
import com.zhlob.auto.captcha.CaptchaDescriptor;
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
    @PostMapping("/login")
    public String loginUser(HttpServletRequest request,
                            @ModelAttribute("captchaDescriptor") CaptchaDescriptor captchaDescriptor) {
        Captcha captcha = Captcha.load(request, "captchaDescriptor");
        boolean isHuman = captcha.validate(captchaDescriptor.getCaptchaCode());
        captchaDescriptor = null;
        return "redirect:/home";
    }

    @GetMapping("/login")
    public ModelAndView getLoginPage() {
        return new ModelAndView("user/register", "captchaDescriptor", new CaptchaDescriptor());
    }
}
