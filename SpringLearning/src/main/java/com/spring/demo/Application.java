package com.spring.demo;

import com.spring.demo.coach.BoxingCoach;
import com.spring.demo.coach.Coach;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext xmlContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Coach coach = xmlContext.getBean("myCoach", Coach.class);
        System.out.println(coach.doDailyWorkout());
        System.out.println(coach.getFortuneService());
        BoxingCoach boxingCoach = xmlContext.getBean("boxingCoach", BoxingCoach.class);
        System.out.println(boxingCoach.doDailyWorkout());
        System.out.println(boxingCoach.getFortuneService());
        System.out.println(boxingCoach.getEmail());
        System.out.println(boxingCoach.getTeam());
        xmlContext.close();
    }
}
