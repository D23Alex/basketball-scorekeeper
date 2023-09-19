package com.d23alex.vtbstat.entities.gameevents;

import com.d23alex.vtbstat.entities.Game;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Table(name="game_endings")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameEnding {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    @ManyToOne Game game;
    Timestamp timestamp;
}
