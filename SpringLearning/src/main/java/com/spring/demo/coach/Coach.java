package com.spring.demo.coach;

import com.spring.demo.fortune.FortuneService;

public interface Coach {
    String doDailyWorkout();

    String getFortuneService();

    void setFortuneService(FortuneService fortuneService);
}
