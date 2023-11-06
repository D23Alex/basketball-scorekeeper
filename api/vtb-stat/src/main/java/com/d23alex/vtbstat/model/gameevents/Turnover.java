package com.d23alex.vtbstat.model.gameevents;

import com.d23alex.vtbstat.payload.statistics.Rules;
import com.d23alex.vtbstat.model.Game;
import com.d23alex.vtbstat.model.Player;
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
