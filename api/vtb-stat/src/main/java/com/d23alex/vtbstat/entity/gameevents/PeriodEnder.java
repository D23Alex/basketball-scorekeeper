package com.d23alex.vtbstat.entity.gameevents;

import com.d23alex.vtbstat.entity.Game;
import com.d23alex.vtbstat.entity.Player;
import com.d23alex.vtbstat.entity.Team;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="period_enders")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
// Нахождение игрока на площадке во время конца периода
public class PeriodEnder {
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
