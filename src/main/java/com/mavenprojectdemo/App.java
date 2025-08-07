package com.mavenprojectdemo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication(scanBasePackages = "com.mavenprojectdemo")
@EnableAspectJAutoProxy
public class App {

    public static void main(String[] args){
        SpringApplication.run(App.class, args);
    }

}
