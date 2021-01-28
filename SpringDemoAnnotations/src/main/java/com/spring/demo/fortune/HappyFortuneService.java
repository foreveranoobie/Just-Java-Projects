package com.spring.demo.fortune;

import org.springframework.stereotype.Component;

@Component
public class HappyFortuneService implements FortuneService {
    @Override
    public String getFortune() {
        return "You're lucky today";
    }
}
