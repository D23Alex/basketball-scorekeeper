package com.d23alex.vtbstat.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Table(name="player_contracts")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerContract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    Player player;
    @ManyToOne
    Team team;
    Date validFrom;
    Date validTo;
    Date terminated;
    Integer jerseyNumber;
}
