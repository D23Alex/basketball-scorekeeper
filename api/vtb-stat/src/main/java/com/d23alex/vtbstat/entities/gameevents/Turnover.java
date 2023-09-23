package com.d23alex.vtbstat.entities.gameevents;

import com.d23alex.vtbstat.game.Rules;
import com.d23alex.vtbstat.entities.Game;
import com.d23alex.vtbstat.entities.Player;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="turnovers")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Turnover {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    @ManyToOne Game game;
    Long millisecondsSinceStart;
    @ManyToOne Player player;
    Rules.TurnoverCause cause;
}
