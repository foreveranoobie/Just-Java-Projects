package com.spring.demo.coach;

import com.spring.demo.fortune.FortuneService;

public interface Coach {
    String getDailyWorkout();

    String getDailyFortune();

    void setFortuneService(FortuneService fortuneService);
}
