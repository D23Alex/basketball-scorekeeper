package com.d23alex.vtbstat.entities.gameevents;

import com.d23alex.vtbstat.entities.Game;
import com.d23alex.vtbstat.entities.Player;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="starting_lineup_occurrences")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StartingLineupOccurrence {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    @ManyToOne Player player;
    @ManyToOne Game game;
}
