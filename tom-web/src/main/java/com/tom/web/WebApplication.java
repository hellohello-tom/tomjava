package com.tom.web;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan("com.tom")
@MapperScan("com.tom.dao")
@EnableSwagger2
public class WebApplication {
    public static void main(String arg[]){
        SpringApplication.run(WebApplication.class, arg);
    }
}
