package com.example.bookstore;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @SpringBootApplication:-it is used on the application class while setting up the spring boot project.
 * it is combination of @configuration, @Enableautocongiguration, @componentscan
 * it will do the component scan but it will scan only subpackages
 */
@SpringBootApplication
/**
 * Spring boot uses simple logging framework by default.
 * By using logger factory we will get the logger for logging messages.
 * logging levels are info, debug, error
 */
@Slf4j
public class BookStoreApplication {
    public static void main(String[] args) {
        System.out.println("welcome to AddressBookApp development");
        //it represents the spring (ioc) container& this is responsible for configuring, identifying,injecting dependency.
        ApplicationContext context=SpringApplication.run(BookStoreApplication.class, args);
        log.info("Book Store started {} environment",context.getEnvironment().getProperty("environment"));
    }
}
