package com.d23alex.vtbstat.entities.gameevents;

import com.d23alex.vtbstat.game.Rules;
import com.d23alex.vtbstat.entities.Game;
import com.d23alex.vtbstat.entities.Player;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="player_ejections")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerEjection implements GameClockTimestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    Game game;
    Long millisecondsSinceStart;
    @ManyToOne
    Player ejectedPlayer;
    Rules.EjectionCause ejectionCause;
}
