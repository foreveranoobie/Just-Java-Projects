package com.epam.storozhuk;

import java.util.Scanner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.epam.storozhuk.domain.User;
import com.epam.storozhuk.scanner.ScannerWrapper;
import com.epam.storozhuk.service.UserService;
import com.epam.storozhuk.status.UserStatus;

public class SpringTheater {
    private User user;
    private ScannerWrapper scannerWrapper;
    private UserService userService;
    private static ApplicationContext context;

    private SpringTheater(ScannerWrapper scannerWrapper) {
        user = new User();
        this.scannerWrapper = scannerWrapper;
    }

    public static void main(String... args) {
        context = new ClassPathXmlApplicationContext("spring.xml");
        SpringTheater springTheater = (SpringTheater) context.getBean("springTheater");
        while (springTheater.user == null) {
        }
        if (springTheater.user.getUserStatus() == UserStatus.ADMIN) {
            springTheater.adminMenu();
        } else {
            springTheater.userMenu();
        }
    }

    private void adminMenu() {

    }

    private void userMenu() {
    }

    private void loginUser() {
        System.out.println("Enter the login: ");
        Scanner scanner = scannerWrapper.getScanner();
        String login = scanner.nextLine();
        UserService userService = (UserService) context.getBean("userService");
        user = userService.authorizeUser(login);
    }
}
