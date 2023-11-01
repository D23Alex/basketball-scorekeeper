package com.d23alex.vtbstat.entity.gameevents;

import com.d23alex.vtbstat.game.Rules;
import com.d23alex.vtbstat.entity.Game;
import com.d23alex.vtbstat.entity.Player;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="turnovers")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Turnover implements GameClockTimestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    Game game;
    Long millisecondsSinceStart;
    @ManyToOne
    Player player;
    Rules.TurnoverCause cause;
    @ManyToOne
    Player stealer; // ссылается на игрока перехватившего мяч, NULL если не было перехвата
}
