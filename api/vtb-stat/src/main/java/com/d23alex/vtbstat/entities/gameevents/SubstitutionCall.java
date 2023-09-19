package com.d23alex.vtbstat.entities.gameevents;

import com.d23alex.vtbstat.entities.Game;
import com.d23alex.vtbstat.entities.Team;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="substitution_calls")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubstitutionCall {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    @ManyToOne Game game;
    Long secondsSinceStart;
    @ManyToOne Team callingTeam;
}
