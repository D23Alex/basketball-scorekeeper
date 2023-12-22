package com.d23alex.vtbstat.payload.performance;

import com.d23alex.vtbstat.model.Team;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PlayerInTeamOverTimePerformance {
    private PlayerOverTimePerformance playerOverTimePerformance;
    private Team team;
}
