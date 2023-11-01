package com.d23alex.vtbstat.entity.gameevents;

import com.d23alex.vtbstat.entity.Game;
import com.d23alex.vtbstat.entity.Player;
import com.d23alex.vtbstat.entity.Team;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="lineup_occurrences")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LineupOccurrence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    Player player;
    Long jerseyNumber;
    @ManyToOne
    Team team;
    @ManyToOne
    Game game;
}
