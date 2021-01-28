package com.spring.demo;

import com.spring.demo.coach.Coach;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanScopeDemoApplication {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext beanScopeXmlContext =
                new ClassPathXmlApplicationContext("beanScope-applicationContext.xml");
        Coach firstCoach = beanScopeXmlContext.getBean("myCoach", Coach.class);
        Coach secondCoach = beanScopeXmlContext.getBean("myCoach", Coach.class);
        System.out.println(firstCoach == secondCoach);
        beanScopeXmlContext.close();
    }
}
