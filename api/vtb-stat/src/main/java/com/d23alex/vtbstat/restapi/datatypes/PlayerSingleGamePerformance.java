package com.d23alex.vtbstat.restapi.datatypes;

import com.d23alex.vtbstat.entities.Game;
import com.d23alex.vtbstat.entities.Player;
import com.d23alex.vtbstat.game.stats.Performance;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlayerSingleGamePerformance {
    private Player player;
    private Game game;
    private Performance performance;
}
