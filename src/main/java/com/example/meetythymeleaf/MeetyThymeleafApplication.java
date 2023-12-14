package com.example.meetythymeleaf;

import com.example.meetythymeleaf.controller.requests.AddUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@SpringBootApplication
public class MeetyThymeleafApplication {
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public AddUserRequest getAddUserRequest() {
        return new AddUserRequest();
    }

    public static void main(String[] args) {
        SpringApplication.run(MeetyThymeleafApplication.class, args);
    }

}
