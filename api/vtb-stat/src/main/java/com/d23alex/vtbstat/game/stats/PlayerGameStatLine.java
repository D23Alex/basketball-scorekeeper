package com.d23alex.vtbstat.game.stats;

import com.d23alex.vtbstat.entities.Player;
import com.d23alex.vtbstat.entities.gameevents.FieldGoalAttempt;
import com.d23alex.vtbstat.entities.gameevents.FreeThrowAttempt;
import com.d23alex.vtbstat.game.GameEventLog;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.function.Supplier;
import java.util.stream.Stream;

import static com.d23alex.vtbstat.entities.gameevents.FieldGoalAttempt.FieldGoalType.*;

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


    public PlayerGameStatLine(Player player, GameEventLog gameEventLog) {
        Supplier<Stream<FieldGoalAttempt>> fgaSupplier = () -> gameEventLog.getFieldGoalAttempts().stream()
                .filter(fga -> player.equals(fga.getShooter()));
        Supplier<Stream<FieldGoalAttempt>> twoPointAttemptsSupplier =
                () -> fgaSupplier.get().filter(fga -> fga.getType() != THREE_POINTER);
        Supplier<Stream<FieldGoalAttempt>> threePointAttemptsSupplier = () -> fgaSupplier.get().filter(fga -> fga.getType() == THREE_POINTER);
        Supplier<Stream<FreeThrowAttempt>> freeThrowAttemptsSupplier = () -> gameEventLog.getFreeThrowAttempts().stream()
                .filter(fta -> player.equals(fta.getShooter()));

        long fieldGoalsAttempted = fgaSupplier.get().count();
        long twoPointersAttempted = twoPointAttemptsSupplier.get().count();
        long threePointersAttempted = threePointAttemptsSupplier.get().count();
        long freeThrowsAttempted = freeThrowAttemptsSupplier.get().count();
        long fieldGoalsMade = fgaSupplier.get().filter(FieldGoalAttempt::getIsSuccessful).count();
        long twoPointersMade = twoPointAttemptsSupplier.get().filter(FieldGoalAttempt::getIsSuccessful).count();
        long threePointersMade = threePointAttemptsSupplier.get().filter(FieldGoalAttempt::getIsSuccessful).count();
        long freeThrowsMade = freeThrowAttemptsSupplier.get().filter(FreeThrowAttempt::getIsSuccessful).count();
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

        this.player = player;
    }


}
