package com.prenotazioni.U5_W1_L5.reservation;

import com.prenotazioni.U5_W1_L5.station.Station;
import com.prenotazioni.U5_W1_L5.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity(name = "reservations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private LocalDate startDate;

    @ManyToOne
    private User user;

    @ManyToOne
    private Station station;
}
