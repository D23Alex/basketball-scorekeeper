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
        this.fieldGoalEfficiency = totals.getFieldGoalsAttempted() == 0 ? 0 :
            (double) totals.getFieldGoalsMade() / totals.getFieldGoalsAttempted();
        this.twoPointEfficiency = totals.getTwoPointersAttempted() == 0 ? 0 :
                (double) totals.getTwoPointersMade() / totals.getTwoPointersAttempted();
        this.threePointEfficiency = totals.getThreePointersAttempted() == 0 ? 0 :
                (double) totals.getThreePointersMade() / totals.getThreePointersAttempted();
        this.freeThrowEfficiency = totals.getFreeThrowsMade() == 0 ? 0 :
                (double) totals.getFreeThrowsMade() / totals.getFreeThrowsAttempted();
    }
}
