package com.d23alex.vtbstat.entities.gameevents;

import com.d23alex.vtbstat.entities.Game;
import com.d23alex.vtbstat.entities.Player;
import com.d23alex.vtbstat.entities.Team;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="after_timeout_court_appearances")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AfterTimeoutCourtAppearance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    @ManyToOne
    Player player;
    @ManyToOne
    Team team;
    @ManyToOne
    Game game;
    @ManyToOne
    Timeout timeout;
}
