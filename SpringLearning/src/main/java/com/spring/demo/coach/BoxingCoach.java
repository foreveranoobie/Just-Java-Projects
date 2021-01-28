package com.spring.demo.coach;

import com.spring.demo.fortune.FortuneService;

public class BoxingCoach implements Coach {
    private FortuneService fortuneService;
    private String email;
    private String team;

    public String doDailyWorkout() {
        return "Do the 12 rounds of sparring";
    }

    public String getFortuneService() {
        return fortuneService.getFortune();
    }

    public void setFortuneService(FortuneService fortuneService) {
        this.fortuneService = fortuneService;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }
}
