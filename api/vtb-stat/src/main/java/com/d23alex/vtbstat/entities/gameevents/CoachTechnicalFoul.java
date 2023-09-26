package com.d23alex.vtbstat.entities.gameevents;

import com.d23alex.vtbstat.entities.Coach;
import com.d23alex.vtbstat.entities.Game;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="coach_technical_fouls")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoachTechnicalFoul implements GameClockTimestamped {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    @ManyToOne Game game;
    Long millisecondsSinceStart;
    @ManyToOne Coach coach;
}
