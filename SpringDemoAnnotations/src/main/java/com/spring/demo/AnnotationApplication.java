package com.spring.demo;

import com.spring.demo.coach.BoxingCoach;
import com.spring.demo.coach.Coach;
import com.spring.demo.coach.SwimCoach;
import com.spring.demo.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnotationApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        SwimCoach coach = context.getBean("swimCoach", SwimCoach.class);
        System.out.println(coach.getDailyWorkout());
        System.out.println(coach.getDailyFortune());
        System.out.println(coach.getEmail());
        System.out.println(coach.getTeam());
        context.close();
    }
}
