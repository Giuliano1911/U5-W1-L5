package com.prenotazioni.U5_W1_L5.reservation;

import com.prenotazioni.U5_W1_L5.station.Station;
import com.prenotazioni.U5_W1_L5.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findAllByUserAndStartDate(User user, LocalDate startDate);

    List<Reservation> findAllByStationAndStartDate(Station station, LocalDate startDate);
}
