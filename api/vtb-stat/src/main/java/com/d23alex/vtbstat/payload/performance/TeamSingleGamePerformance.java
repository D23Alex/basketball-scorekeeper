package com.d23alex.vtbstat.payload.performance;

import com.d23alex.vtbstat.model.Game;
import com.d23alex.vtbstat.model.Team;
import com.d23alex.vtbstat.payload.statistics.Performance;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TeamSingleGamePerformance {
    private Team team;
    private Game game;
    private Performance performance;
}
