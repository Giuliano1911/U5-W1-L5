package com.prenotazioni.U5_W1_L5.building;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildingRepository extends JpaRepository<Building, Long> {

    public Building findById(long id);
}
