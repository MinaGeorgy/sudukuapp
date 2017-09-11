package com.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootClass  {


    private static final Logger log = LoggerFactory.getLogger(SpringBootClass.class);


    public static void main(String[] args) {
        SpringApplication.run(SpringBootClass.class, args);
    }

}
