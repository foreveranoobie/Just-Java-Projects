package com.spring.demo.coach;

import com.spring.demo.fortune.FortuneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class BoxingCoach implements Coach {
    @Autowired
    @Qualifier("randomFortuneService")
    private FortuneService fortuneService;

    public BoxingCoach() {
    }

    public BoxingCoach(FortuneService fortuneService) {
        this.fortuneService = fortuneService;
    }

    @Override
    public String getDailyWorkout() {
        return "Do 12 rounds of sparring";
    }

    public String getDailyFortune() {
        return fortuneService.getFortune();
    }

    @Override
    public void setFortuneService(FortuneService fortuneService) {
        this.fortuneService = fortuneService;
    }

/*    @PostConstruct
    private void welcomeCoach(){
        System.out.println("Hello, I'm your boxing coach.");
    }

    @PreDestroy
    private void byeCoach(){
        System.out.println("Have a productive recovery! Good Bye!");
    }*/
}
