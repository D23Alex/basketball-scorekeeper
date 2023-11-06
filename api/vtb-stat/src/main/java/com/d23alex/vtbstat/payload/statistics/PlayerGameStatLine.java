package com.d23alex.vtbstat.payload.statistics;

import com.d23alex.vtbstat.model.Player;
import com.d23alex.vtbstat.model.gameevents.FieldGoalAttempt;
import com.d23alex.vtbstat.model.gameevents.FreeThrowAttempt;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import static com.d23alex.vtbstat.model.gameevents.FieldGoalAttempt.FieldGoalType.*;

@Data
@Builder
@AllArgsConstructor
public class PlayerGameStatLine {
    private Player player;

    private long fieldGoalsAttempted;
    private long twoPointersAttempted;
    private long threePointersAttempted;
    private long freeThrowsAttempted;
    private long fieldGoalsMade;
    private long twoPointersMade;
    private long threePointersMade;
    private long freeThrowsMade;
    private long fieldGoalsMissed;
    private long twoPointersMissed;
    private long threePointersMissed;
    private long freeThrowsMissed;

    private double fieldGoalEfficiency;
    private double twoPointEfficiency;
    private double threePointEfficiency;
    private double freeThrowEfficiency;

    private long points;
    private long assists;
    private long rebounds;
    private long blocks;
    private long steals;
    private long turnovers;
    private long personalFouls;
    private long technicalFouls;

    private long timePlayedInMillis;


    public PlayerGameStatLine(Player player, GameEventLog gameEventLog) {
        var fgaSupplier = gameEventLog.getFieldGoalAttempts().stream()
                .filter(fga -> player.equals(fga.getShooter())).toList();
        var twoPointAttemptsSupplier =
                fgaSupplier.stream().filter(fga -> fga.getType() != THREE_POINTER).toList();
        var threePointAttemptsSupplier = fgaSupplier.stream().filter(fga -> fga.getType() == THREE_POINTER).toList();
        var freeThrowAttemptsSupplier = gameEventLog.getFreeThrowAttempts().stream()
                .filter(fta -> player.equals(fta.getShooter())).toList();

        long fieldGoalsAttempted = fgaSupplier.size();
        long twoPointersAttempted = twoPointAttemptsSupplier.size();
        long threePointersAttempted = threePointAttemptsSupplier.size();
        long freeThrowsAttempted = freeThrowAttemptsSupplier.size();
        long fieldGoalsMade = fgaSupplier.stream().filter(FieldGoalAttempt::getIsSuccessful).count();
        long twoPointersMade = twoPointAttemptsSupplier.stream().filter(FieldGoalAttempt::getIsSuccessful).count();
        long threePointersMade = threePointAttemptsSupplier.stream().filter(FieldGoalAttempt::getIsSuccessful).count();
        long freeThrowsMade = freeThrowAttemptsSupplier.stream().filter(FreeThrowAttempt::getIsSuccessful).count();
        long fieldGoalsMissed = fieldGoalsAttempted - fieldGoalsMade;
        long twoPointersMissed = twoPointersAttempted - twoPointersMade;
        long threePointersMissed = threePointersAttempted - threePointersMade;
        long freeThrowsMissed = freeThrowsAttempted - freeThrowsMade;

        double fieldGoalEfficiency = (double) fieldGoalsMade / fieldGoalsAttempted;
        double twoPointEfficiency = (double) twoPointersMade / twoPointersAttempted;
        double threePointEfficiency = (double) threePointersMade / threePointersAttempted;
        double freeThrowEfficiency = (double) freeThrowsMade / freeThrowsAttempted;

        long points = twoPointersMade * 2 + threePointersMade * 3 + freeThrowsMade;
        long assists = gameEventLog.getFieldGoalAttempts().stream()
                .filter(fga -> player.equals(fga.getAssistant())).count();
        long rebounds = gameEventLog.getFieldGoalAttempts().stream()
                .filter(fga -> player.equals(fga.getReboundedBy())).count();
        long blocks = gameEventLog.getFieldGoalAttempts().stream()
                .filter(fga -> player.equals(fga.getBlockedBy())).count();
        long turnovers = gameEventLog.getTurnovers().stream()
                .filter(turnover -> player.equals(turnover.getPlayer())).count();
        long steals = gameEventLog.getTurnovers().stream()
                .filter(turnover -> player.equals(turnover.getStealer())).count();
        long personalFouls = gameEventLog.getPersonalFouls().stream()
                .filter(personalFoul -> player.equals(personalFoul.getFoulingPlayer())).count();
        long technicalFouls = gameEventLog.getPlayerTechnicalFouls().stream()
                .filter(personalFoul -> player.equals(personalFoul.getFoulingPlayer())).count();

        long timePlayedInMillis = Time.timePlayedInMillis(player, gameEventLog);

        this.fieldGoalsAttempted = fieldGoalsAttempted;
        this.twoPointersAttempted = twoPointersAttempted;
        this.threePointersAttempted = threePointersAttempted;
        this.freeThrowsAttempted = freeThrowsAttempted;
        this.fieldGoalsMade = fieldGoalsMade;
        this.twoPointersMade = twoPointersMade;
        this.threePointersMade = threePointersMade;
        this.freeThrowsMade = freeThrowsMade;
        this.fieldGoalsMissed = fieldGoalsMissed;
        this.twoPointersMissed = twoPointersMissed;
        this.threePointersMissed = threePointersMissed;
        this.freeThrowsMissed = freeThrowsMissed;
        this.fieldGoalEfficiency = fieldGoalEfficiency;
        this.twoPointEfficiency = twoPointEfficiency;
        this.threePointEfficiency = threePointEfficiency;
        this.freeThrowEfficiency = freeThrowEfficiency;
        this.points = points;
        this.assists = assists;
        this.rebounds = rebounds;
        this.blocks = blocks;
        this.turnovers = turnovers;
        this.steals = steals;
        this.personalFouls = personalFouls;
        this.technicalFouls = technicalFouls;
        this.timePlayedInMillis = timePlayedInMillis;

        this.player = player;
    }

}
