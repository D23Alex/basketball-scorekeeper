package com.d23alex.vtbstat.model.gameevents;

import com.d23alex.vtbstat.payload.statistics.Rules;
import com.d23alex.vtbstat.model.Game;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="coach_ejections")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class CoachEjection implements GameClockTimestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne Game game;
    Long millisecondsSinceStart;
    Rules.EjectionCause ejectionCause;
}
