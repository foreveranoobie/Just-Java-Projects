package com.spring.demo.fortune;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomFortuneService implements FortuneService {
    private String[] fortunes;
    private Random random;

    public RandomFortuneService() {
        fortunes = new String[]{"Diligence is the mother of good luck",
                "Beware of the wolf in sheep's clothing",
                "The journey is the reward"};
        random = new Random();
    }

    @Override
    public String getFortune() {
        return fortunes[random.nextInt(fortunes.length)];
    }
}
