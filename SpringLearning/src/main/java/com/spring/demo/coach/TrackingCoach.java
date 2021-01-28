package com.spring.demo.coach;

import com.spring.demo.fortune.FortuneService;

public class TrackingCoach implements Coach {
    private FortuneService fortuneService;

    public TrackingCoach() {
    }

    public TrackingCoach(FortuneService fortuneService) {
        this.fortuneService = fortuneService;
    }

    public String doDailyWorkout() {
        return "Do running 5km";
    }

    public String getFortuneService() {
        return fortuneService.getFortune();
    }

    public void setFortuneService(FortuneService fortuneService) {
        this.fortuneService = fortuneService;
    }

    private void welcomeCoach() {
        System.out.println("Hello, I'm a tracking coach. Let's do some training");
    }

    private void byeCoach() {
        System.out.println("Good Bye. Have a good rest!");
    }
}
