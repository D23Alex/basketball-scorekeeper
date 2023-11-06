package com.d23alex.vtbstat.payload.statistics;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Efficiency {
    private double fieldGoalEfficiency = 0;
    private double twoPointEfficiency = 0;
    private double threePointEfficiency = 0;
    private double freeThrowEfficiency = 0;

    public Efficiency(Totals totals) {
        if (totals.getFieldGoalsAttempted() == 0 ||
                totals.getTwoPointersAttempted() == 0 ||
                totals.getThreePointersAttempted() == 0 ||
                totals.getFreeThrowsAttempted() == 0)
            return;
        this.fieldGoalEfficiency = (double) totals.getFieldGoalsMade() / totals.getFieldGoalsAttempted();
        this.twoPointEfficiency = (double) totals.getTwoPointersMade() / totals.getTwoPointersAttempted();
        this.threePointEfficiency = (double) totals.getThreePointersMade() / totals.getThreePointersAttempted();
        this.freeThrowEfficiency = (double) totals.getFreeThrowsMade() / totals.getFreeThrowsAttempted();
    }
}
