package com.d23alex.vtbstat.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Table(name="games")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Game {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    @ManyToOne Arena arena;
    @ManyToOne Team team1;
    @ManyToOne Team team2;
    @ManyToOne Team homeTeam;
    Timestamp scheduledStartTime;
}
