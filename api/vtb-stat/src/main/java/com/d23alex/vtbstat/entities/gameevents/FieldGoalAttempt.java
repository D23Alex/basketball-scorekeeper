package com.d23alex.vtbstat.entities.gameevents;

import com.d23alex.vtbstat.entities.Game;
import com.d23alex.vtbstat.entities.Player;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="field_goal_attempts")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FieldGoalAttempt {
    public enum FieldGoalType {
        TWO_POINTER,
        THREE_POINTER,
        DUNK
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    @ManyToOne Game game;
    Long secondsSinceStart;
    FieldGoalType type;
    @ManyToOne Player shooter;
    Boolean isSuccessful;
    @OneToOne PersonalFoul foulOnShooter; // NULL если фола на броске не было
    @ManyToOne Player assistant; // NULL если мяч не был забит после передачи
    @ManyToOne Player blockedBy; // NULL если мяч не был заблокирован
}
