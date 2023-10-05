package com.d23alex.vtbstat.restapi.datatypes;

import com.d23alex.vtbstat.entities.Game;
import com.d23alex.vtbstat.entities.Player;
import com.d23alex.vtbstat.game.stats.Performance;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
public class PlayerOverTimePerformance {
    private Player player;
    private Timestamp from;
    private Timestamp to;
    private List<Game> games;
    private Performance performance;
}
