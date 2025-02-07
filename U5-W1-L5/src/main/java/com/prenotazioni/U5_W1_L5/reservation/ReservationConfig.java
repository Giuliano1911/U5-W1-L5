package com.prenotazioni.U5_W1_L5.reservation;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.time.LocalDate;

@Configuration
public class ReservationConfig {

    @Autowired
    private Faker faker;

    @Bean
    @Scope("prototype")
    public Reservation reservation() {
        Reservation r = new Reservation();
        r.setStartDate(LocalDate.now().plusDays(faker.number().numberBetween(2, 5)));
        return r;
    }
}
