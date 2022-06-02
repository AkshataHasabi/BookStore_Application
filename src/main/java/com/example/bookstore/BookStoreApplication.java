package com.example.bookstore;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@Slf4j
public class BookStoreApplication {

    public static void main(String[] args) {
        System.out.println("welcome to AddressBookApp development");
        ApplicationContext context=SpringApplication.run(BookStoreApplication.class, args);
        log.info("Book Store started {} environment",context.getEnvironment().getProperty("environment"));
    }
}
