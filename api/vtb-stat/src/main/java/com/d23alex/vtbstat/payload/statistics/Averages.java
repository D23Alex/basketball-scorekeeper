package com.d23alex.vtbstat.payload.statistics;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Averages {
    private double fieldGoalsAttempted = 0;
    private double twoPointersAttempted = 0;
    private double threePointersAttempted = 0;
    private double freeThrowsAttempted = 0;
    private double fieldGoalsMade = 0;
    private double twoPointersMade = 0;
    private double threePointersMade = 0;
    private double freeThrowsMade = 0;
    private double fieldGoalsMissed = 0;
    private double twoPointersMissed = 0;
    private double threePointersMissed = 0;
    private double freeThrowsMissed = 0;
    private double points = 0;
    private double assists = 0;
    private double rebounds = 0;
    private double blocks = 0;
    private double steals = 0;
    private double turnovers = 0;
    private double personalFouls = 0;
    private double technicalFouls = 0;
    private double timePlayedInMillis = 0;

    public Averages(Totals totals, long games) {
        if (games == 0)
            return;
        this.fieldGoalsAttempted = (double) totals.getFieldGoalsAttempted() / games;
        this.twoPointersAttempted = (double) totals.getTwoPointersAttempted() / games;
        this.threePointersAttempted = (double) totals.getThreePointersAttempted() / games;
        this.freeThrowsAttempted = (double) totals.getFreeThrowsAttempted() / games;
        this.fieldGoalsMade = (double) totals.getFieldGoalsMade() / games;
        this.twoPointersMade = (double) totals.getTwoPointersMade() / games;
        this.threePointersMade = (double) totals.getThreePointersMade() / games;
        this.freeThrowsMade = (double) totals.getFreeThrowsMade() / games;
        this.fieldGoalsMissed = (double) totals.getFieldGoalsMissed() / games;
        this.twoPointersMissed = (double) totals.getTwoPointersMissed() / games;
        this.threePointersMissed = (double) totals.getThreePointersMissed() / games;
        this.freeThrowsMissed = (double) totals.getFreeThrowsMissed() / games;
        this.points = (double) totals.getPoints() / games;
        this.assists = (double) totals.getAssists() / games;
        this.rebounds = (double) totals.getRebounds() / games;
        this.blocks = (double) totals.getBlocks() / games;
        this.steals = (double) totals.getSteals() / games;
        this.turnovers = (double) totals.getTurnovers() / games;
        this.personalFouls = (double) totals.getPersonalFouls() / games;
        this.technicalFouls = (double) totals.getTechnicalFouls() / games;
        this.timePlayedInMillis = (double) totals.getTimePlayedInMillis() / games;
    }
}
