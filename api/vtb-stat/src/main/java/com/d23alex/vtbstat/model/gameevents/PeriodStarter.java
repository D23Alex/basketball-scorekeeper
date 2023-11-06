package com.d23alex.vtbstat.model.gameevents;

import com.d23alex.vtbstat.model.Game;
import com.d23alex.vtbstat.model.Player;
import com.d23alex.vtbstat.model.Team;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="period_starters")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PeriodStarter {
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
