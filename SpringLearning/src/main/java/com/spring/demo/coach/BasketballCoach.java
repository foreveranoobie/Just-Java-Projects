package com.spring.demo.coach;

import com.spring.demo.fortune.FortuneService;

public class BasketballCoach implements Coach {
    private FortuneService fortuneService;

    public BasketballCoach(){}

    public BasketballCoach(FortuneService fortuneService) {
        this.fortuneService = fortuneService;
    }

    public String doDailyWorkout() {
        return "Do basketball training";
    }

    public void setFortuneService(FortuneService fortuneService) {
        this.fortuneService = fortuneService;
    }

    public String getFortuneService() {
        return fortuneService.getFortune();
    }
}
