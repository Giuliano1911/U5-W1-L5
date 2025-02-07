package com.prenotazioni.U5_W1_L5.station;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class StationConfig {

    @Autowired
    private Faker faker;

    @Bean
    @Scope("prototype")
    public Station station() {
        Station s = new Station();
        s.setStationType(StationType.getRandomType());
        s.setDescription(faker.lorem().sentence(5));
        s.setCapacity(faker.number().numberBetween(5, 20));
        return s;
    }
}
