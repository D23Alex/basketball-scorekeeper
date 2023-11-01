package com.d23alex.vtbstat.entity.gameevents;

import com.d23alex.vtbstat.entity.Game;
import com.d23alex.vtbstat.entity.Team;
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
