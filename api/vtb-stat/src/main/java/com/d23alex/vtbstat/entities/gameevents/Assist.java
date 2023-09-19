package com.d23alex.vtbstat.entities.gameevents;

import com.d23alex.vtbstat.entities.Game;
import com.d23alex.vtbstat.entities.Player;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="assists")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Assist {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    @ManyToOne Game game;
    Long secondsSinceStart;
    @ManyToOne Player passer;
    @ManyToOne FieldGoalAttempt fieldGoalAttempt;
}
