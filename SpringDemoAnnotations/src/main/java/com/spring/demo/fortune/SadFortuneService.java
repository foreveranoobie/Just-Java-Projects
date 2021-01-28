package com.spring.demo.fortune;

public class SadFortuneService implements FortuneService{
    @Override
    public String getFortune() {
        return "Sorry, but it's not your day.";
    }
}
