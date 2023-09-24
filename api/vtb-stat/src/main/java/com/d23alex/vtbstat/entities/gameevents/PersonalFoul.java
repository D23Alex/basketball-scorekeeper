package com.d23alex.vtbstat.entities.gameevents;

import com.d23alex.vtbstat.entities.Game;
import com.d23alex.vtbstat.entities.Player;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="personal_fouls")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonalFoul {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    Game game;
    Long millisecondsSinceStart;
    Boolean isUnsportsmanlike;
    @ManyToOne
    Player foulingPlayer;
    @ManyToOne
    Player fouledPlayer;
}
