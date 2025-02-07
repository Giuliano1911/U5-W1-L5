package com.prenotazioni.U5_W1_L5.building;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class BuildingConfig {

    @Autowired
    private Faker faker;

    @Bean
    @Scope("prototype")
    public Building building() {
        Building b = new Building();
        b.setBuildingName(faker.company().name());
        b.setCity(faker.address().cityName());
        b.setAddress(faker.address().streetAddress());
        return b;
    }
}
