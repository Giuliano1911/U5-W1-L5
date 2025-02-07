package com.prenotazioni.U5_W1_L5.station;

import com.prenotazioni.U5_W1_L5.building.Building;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StationRepository extends JpaRepository<Station, Long> {

    public List<Station> findAllByStationTypeAndBuilding(StationType stationType, Building building);
}
