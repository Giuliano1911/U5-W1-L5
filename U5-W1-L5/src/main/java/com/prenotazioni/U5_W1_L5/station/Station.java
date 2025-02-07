package com.prenotazioni.U5_W1_L5.station;

import com.prenotazioni.U5_W1_L5.building.Building;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "stations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(nullable = false)
    private StationType stationType;

    private String description;

    private int capacity;

    @ManyToOne
    private Building building;

}
