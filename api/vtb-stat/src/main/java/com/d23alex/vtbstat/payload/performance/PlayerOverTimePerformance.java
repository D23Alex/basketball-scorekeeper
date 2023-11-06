package com.d23alex.vtbstat.payload.performance;

import com.d23alex.vtbstat.model.Game;
import com.d23alex.vtbstat.model.Player;
import com.d23alex.vtbstat.payload.statistics.Performance;
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
