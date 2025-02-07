package com.prenotazioni.U5_W1_L5.runners;

import com.prenotazioni.U5_W1_L5.building.Building;
import com.prenotazioni.U5_W1_L5.building.BuildingRepository;
import com.prenotazioni.U5_W1_L5.reservation.Reservation;
import com.prenotazioni.U5_W1_L5.reservation.ReservationRepository;
import com.prenotazioni.U5_W1_L5.station.Station;
import com.prenotazioni.U5_W1_L5.station.StationRepository;
import com.prenotazioni.U5_W1_L5.station.StationType;
import com.prenotazioni.U5_W1_L5.user.User;
import com.prenotazioni.U5_W1_L5.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

@Component
@RequiredArgsConstructor
@Transactional
@Order(2)
public class ApplicationRunner implements CommandLineRunner {

    private final Scanner scanner = new Scanner(System.in);

    private final BuildingRepository buildingRepository;
    private final ReservationRepository reservationRepository;
    private final StationRepository stationRepository;
    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Sistema prenotazione uffici");
        System.out.println("Chi vuole effettuare una prenotazione?");
        List<User> userList = userRepository.findAll();
        User user;
        while (true) {
            for (int i = 1; i <= userList.size(); i++)
                System.out.println(i + ". " + userList.get(i - 1).getUsername());
            try {
                int input = Integer.parseInt(scanner.nextLine());
                if (userList.size() >= input && input > 0) {
                    user = userList.get(input - 1);
                    break;
                } else System.out.println("Numero non valido");
            } catch (NumberFormatException e) {
                System.out.println("Si prega di inserire un numero");
            }
        }
        List<Building> buildingList = buildingRepository.findAll();
        Building building;
        System.out.println("In che città vuoi effettuare la prenotazione?");
        while (true) {
            for (int i = 1; i <= buildingList.size(); i++)
                System.out.println(i + ". " + buildingList.get(i - 1).getCity());
            try {
                int input = Integer.parseInt(scanner.nextLine());
                if (buildingList.size() >= input && input > 0) {
                    building = buildingList.get(input - 1);
                    break;
                } else System.out.println("Numero non valido");
            } catch (NumberFormatException e) {
                System.out.println("Si prega di inserire un numero");
            }
        }
        List<Station> stationList;
        System.out.println("Che tipo di ufficio ti serve?");
        while (true) {
            System.out.println("1. OPEN SPACE");
            System.out.println("2. UFFICIO PRIVATO");
            System.out.println("3. SALA RIUNIONI");
            try {
                int input = Integer.parseInt(scanner.nextLine());
                StationType stationType = StationType.MEETINGROOM;
                if (input <= 3 && input > 0) {
                    stationType = switch (input) {
                        case 1 -> StationType.OPENSPACE;
                        case 2 -> StationType.PRIVATE;
                        default -> stationType;
                    };
                    stationList = stationRepository.findAllByStationTypeAndBuilding(stationType, building);
                    if (stationList.isEmpty()) {
                        System.out.println("Non ci sono uffici del tipo richiesto");
                    } else break;
                } else System.out.println("Inserire un numero valido");
            } catch (NumberFormatException e) {
                System.out.println("Si prega di inserire un numero");
            }
        }
        System.out.println("Scegli l'ufficio");
        Station station;
        while (true) {
            for (int i = 1; i <= stationList.size(); i++)
                System.out.println(i + ". " + stationList.get(i - 1));
            try {
                int input = Integer.parseInt(scanner.nextLine());
                if (stationList.size() >= input && input > 0) {
                    station = stationList.get(input - 1);
                    break;
                } else System.out.println("Numero non valido");
            } catch (NumberFormatException e) {
                System.out.println("Si prega di inserire un numero");
            }
        }
        LocalDate date;
        while (true) {
            while (true) {
                try {
                    System.out.println("Inserisci data prenotazione (yyyy-mm-dd)");
                    date = LocalDate.parse(scanner.nextLine());
                    break;
                } catch (DateTimeParseException e) {
                    System.out.println("Tempo non valido, inserisci formato (yyyy-mm-dd)");
                }
            }
            if (reservationRepository.findAllByUserAndStartDate(user, date).isEmpty()) {
                if (reservationRepository.findAllByStationAndStartDate(station, date).isEmpty()) {
                    Reservation reservation = new Reservation();
                    reservation.setStation(station);
                    reservation.setUser(user);
                    reservation.setStartDate(date);
                    reservationRepository.save(reservation);
                    System.out.println("Prenotazione effettuata con successo! Riepilogo:");
                    System.out.println("Prenotazione effettuata da " + user.getUsername() + ", nell'edificio " + building.getBuildingName() + ", indirizzo " + building.getAddress() + ", " + building.getCity() + ", in data " + date);
                    break;
                } else System.out.println("Esiste già una prenotazione in questo edificio per quel giorno!");
            } else System.out.println("Hai già una prenotazione in questa data!");
        }
        System.out.println("Arrivederci!");
    }
}