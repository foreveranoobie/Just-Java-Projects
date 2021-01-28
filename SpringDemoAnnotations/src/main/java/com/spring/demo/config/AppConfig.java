package com.spring.demo.config;

import com.spring.demo.coach.Coach;
import com.spring.demo.coach.SwimCoach;
import com.spring.demo.fortune.FortuneService;
import com.spring.demo.fortune.SadFortuneService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.spring.demo")
@PropertySource("classpath:info.properties")
public class AppConfig {
    @Bean
    public FortuneService sadFortuneService() {
        return new SadFortuneService();
    }

    @Bean
    public Coach swimCoach() {
        Coach swimCoach = new SwimCoach();
        swimCoach.setFortuneService(sadFortuneService());
        return swimCoach;
    }
}
