package com.d23alex.vtbstat.model.gameevents;

import com.d23alex.vtbstat.model.Game;
import com.d23alex.vtbstat.model.Team;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="timeouts")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Timeout implements GameClockTimestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    Game game;
    Long millisecondsSinceStart;
    @ManyToOne
    Team callingTeam;
}
