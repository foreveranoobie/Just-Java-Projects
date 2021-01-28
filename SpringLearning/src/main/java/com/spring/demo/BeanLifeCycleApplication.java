package com.spring.demo;

import com.spring.demo.coach.Coach;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanLifeCycleApplication {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext xmlContext = new ClassPathXmlApplicationContext("beanLifeCycle-applicationContext.xml");
        Coach coach = xmlContext.getBean("myCoach", Coach.class);
        System.out.println(coach.doDailyWorkout());
        System.out.println(coach.getFortuneService());
        xmlContext.close();
    }
}
