package com.d23alex.vtbstat.payload.performance;

import com.d23alex.vtbstat.model.Game;
import com.d23alex.vtbstat.model.Player;
import com.d23alex.vtbstat.payload.statistics.Performance;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlayerSingleGamePerformance {
    private Player player;
    private Game game;
    private Performance performance;
}
