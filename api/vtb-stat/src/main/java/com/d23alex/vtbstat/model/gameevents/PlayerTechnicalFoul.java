package com.d23alex.vtbstat.model.gameevents;

import com.d23alex.vtbstat.model.Game;
import com.d23alex.vtbstat.model.Player;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="player_technical_fouls")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerTechnicalFoul implements GameClockTimestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    Game game;
    Long millisecondsSinceStart;
    @ManyToOne
    Player foulingPlayer;
}
