package com.d23alex.vtbstat.model.gameevents;

import com.d23alex.vtbstat.model.Game;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Table(name="period_starts")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PeriodStart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    Game game;
    Timestamp timestamp;
}
