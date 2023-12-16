package com.d23alex.vtbstat.model.gameevents;

import com.d23alex.vtbstat.model.Game;
import com.d23alex.vtbstat.model.Player;
import com.d23alex.vtbstat.model.Team;
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
    // TODO: might want to remove it from here, as the jersey number is specified in player's contract
    Long jerseyNumber;
    @ManyToOne
    Team team;
    @ManyToOne
    Game game;
}
