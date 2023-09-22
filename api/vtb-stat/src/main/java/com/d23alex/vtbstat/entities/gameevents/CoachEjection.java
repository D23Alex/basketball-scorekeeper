package com.d23alex.vtbstat.entities.gameevents;

import com.d23alex.vtbstat.game.Rules;
import com.d23alex.vtbstat.entities.Game;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="coach_ejections")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoachEjection {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    @ManyToOne Game game;
    Long secondsSinceStart;
    Rules.EjectionCause ejectionCause;
}
