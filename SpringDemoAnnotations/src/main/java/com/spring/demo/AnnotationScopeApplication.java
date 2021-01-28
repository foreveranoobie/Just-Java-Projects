package com.spring.demo;

import com.spring.demo.coach.Coach;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationScopeApplication {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Coach firstCoach = context.getBean("boxingCoach", Coach.class);
        Coach secondCoach = context.getBean("boxingCoach", Coach.class);
        System.out.println(firstCoach == secondCoach);
        context.close();
    }
}
