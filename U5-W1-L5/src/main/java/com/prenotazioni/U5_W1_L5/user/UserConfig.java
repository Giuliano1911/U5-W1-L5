package com.prenotazioni.U5_W1_L5.user;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class UserConfig {

    @Autowired
    private Faker faker;

    @Bean
    @Scope("prototype")
    public User user() {
        User u = new User();
        u.setUsername(faker.name().username());
        u.setName(faker.name().fullName());
        u.setEmail(faker.internet().emailAddress());
        return u;
    }
}
