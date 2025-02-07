package com.prenotazioni.U5_W1_L5.runners;

import com.github.javafaker.Faker;
import com.prenotazioni.U5_W1_L5.building.Building;
import com.prenotazioni.U5_W1_L5.building.BuildingRepository;
import com.prenotazioni.U5_W1_L5.reservation.Reservation;
import com.prenotazioni.U5_W1_L5.reservation.ReservationRepository;
import com.prenotazioni.U5_W1_L5.station.Station;
import com.prenotazioni.U5_W1_L5.station.StationRepository;
import com.prenotazioni.U5_W1_L5.user.User;
import com.prenotazioni.U5_W1_L5.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Order(1)
public class CreateDBRunner implements CommandLineRunner {

    private final Faker faker;

    private final BuildingRepository buildingRepository;
    private final ReservationRepository reservationRepository;
    private final StationRepository stationRepository;
    private final UserRepository userRepository;

    private final Building building1, building2, building3;

    private final Station station1, station2, station3, station4, station5, station6, station7, station8, station9, station10, station11, station12, station13, station14, station15;

    private final User user1, user2, user3, user4, user5, user6, user7, user8, user9, user10;

    private final Reservation reservation1, reservation2, reservation3, reservation4, reservation5;

    @Override
    public void run(String... args) throws Exception {

        List<User> users = new ArrayList<>(List.of(user1, user2, user3, user4, user5, user6, user7, user8, user9, user10));
        for (User u : users)
            userRepository.save(u);

        List<Building> buildings = new ArrayList<>(List.of(building1, building2, building3));
        for (Building b : buildings)
            buildingRepository.save(b);

        List<Station> stations = new ArrayList<>(List.of(station1, station2, station3, station4, station5, station6, station7, station8, station9, station10, station11, station12, station13, station14, station15));
        for (int i = 0; i < stations.size(); i++) {
            stations.get(i).setBuilding(building3);
            if (i < 10) stations.get(i).setBuilding(building2);
            if (i < 5) stations.get(i).setBuilding(building1);
            stationRepository.save(stations.get(i));
        }

        List<Reservation> reservations = new ArrayList<>(List.of(reservation1, reservation2, reservation3, reservation4, reservation5));
        for (Reservation r : reservations) {
            r.setUser(users.get(faker.number().numberBetween(0, users.size())));
            r.setStation(stations.get(faker.number().numberBetween(0, stations.size())));
            reservationRepository.save(r);
        }
    }
}
