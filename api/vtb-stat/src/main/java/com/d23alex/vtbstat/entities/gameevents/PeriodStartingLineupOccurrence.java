package com.d23alex.vtbstat.entities.gameevents;

import com.d23alex.vtbstat.entities.Game;
import com.d23alex.vtbstat.entities.Player;
import com.d23alex.vtbstat.entities.Team;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="period_starting_lineup_occurrences")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PeriodStartingLineupOccurrence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    Player player;
    @ManyToOne
    Team team;
    @ManyToOne
    Game game;
    Long period;
}
