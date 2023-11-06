package com.d23alex.vtbstat.model.gameevents;

import com.d23alex.vtbstat.model.Game;
import com.d23alex.vtbstat.model.Player;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="personal_fouls")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonalFoul implements GameClockTimestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    Game game;
    Long millisecondsSinceStart;
    Boolean isUnsportsmanlike;
    Boolean isOffensive;
    @ManyToOne
    Player foulingPlayer;
    @ManyToOne
    Player fouledPlayer;
}
