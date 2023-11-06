package com.d23alex.vtbstat.payload.statistics;

import com.d23alex.vtbstat.model.Player;
import com.d23alex.vtbstat.model.gameevents.FieldGoalAttempt;
import com.d23alex.vtbstat.model.gameevents.FreeThrowAttempt;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.d23alex.vtbstat.model.gameevents.FieldGoalAttempt.FieldGoalType.THREE_POINTER;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Totals {

    private long fieldGoalsAttempted = 0;
    private long twoPointersAttempted = 0;
    private long threePointersAttempted = 0;
    private long freeThrowsAttempted = 0;
    private long fieldGoalsMade = 0;
    private long twoPointersMade = 0;
    private long threePointersMade = 0;
    private long freeThrowsMade = 0;
    private long fieldGoalsMissed = 0;
    private long twoPointersMissed = 0;
    private long threePointersMissed = 0;
    private long freeThrowsMissed = 0;
    private long points = 0;
    private long assists = 0;
    private long rebounds = 0;
    private long blocks = 0;
    private long steals = 0;
    private long turnovers = 0;
    private long personalFouls = 0;
    private long technicalFouls = 0;
    private long timePlayedInMillis = 0;


    public Totals(Player player, GameEventLog gameEventLog) {
        var fieldGoalAttempts = gameEventLog.getFieldGoalAttempts().stream()
                .filter(fga -> player.equals(fga.getShooter())).toList();
        var twoPointAttempts =
                fieldGoalAttempts.stream().filter(fga -> fga.getType() != THREE_POINTER).toList();
        var threePointAttempts = fieldGoalAttempts.stream().filter(fga -> fga.getType() == THREE_POINTER).toList();
        var freeThrowAttempts = gameEventLog.getFreeThrowAttempts().stream()
                .filter(fta -> player.equals(fta.getShooter())).toList();

        this.fieldGoalsAttempted = fieldGoalAttempts.size();
        this.twoPointersAttempted = twoPointAttempts.size();
        this.threePointersAttempted = threePointAttempts.size();
        this.freeThrowsAttempted = freeThrowAttempts.size();
        this.fieldGoalsMade = fieldGoalAttempts.stream().filter(FieldGoalAttempt::getIsSuccessful).count();
        this.twoPointersMade = twoPointAttempts.stream().filter(FieldGoalAttempt::getIsSuccessful).count();
        this.threePointersMade = threePointAttempts.stream().filter(FieldGoalAttempt::getIsSuccessful).count();
        this.freeThrowsMade = freeThrowAttempts.stream().filter(FreeThrowAttempt::getIsSuccessful).count();
        this.fieldGoalsMissed = fieldGoalsAttempted - fieldGoalsMade;
        this.twoPointersMissed = twoPointersAttempted - twoPointersMade;
        this.threePointersMissed = threePointersAttempted - threePointersMade;
        this.freeThrowsMissed = freeThrowsAttempted - freeThrowsMade;

        this.points = twoPointersMade * 2 + threePointersMade * 3 + freeThrowsMade;
        this.assists = gameEventLog.getFieldGoalAttempts().stream()
                .filter(fga -> player.equals(fga.getAssistant())).count();
        this.rebounds = gameEventLog.getFieldGoalAttempts().stream()
                .filter(fga -> player.equals(fga.getReboundedBy())).count();
        this.blocks = gameEventLog.getFieldGoalAttempts().stream()
                .filter(fga -> player.equals(fga.getBlockedBy())).count();
        this.turnovers = gameEventLog.getTurnovers().stream()
                .filter(turnover -> player.equals(turnover.getPlayer())).count();
        this.steals = gameEventLog.getTurnovers().stream()
                .filter(turnover -> player.equals(turnover.getStealer())).count();
        this.personalFouls = gameEventLog.getPersonalFouls().stream()
                .filter(personalFoul -> player.equals(personalFoul.getFoulingPlayer())).count();
        this.technicalFouls = gameEventLog.getPlayerTechnicalFouls().stream()
                .filter(personalFoul -> player.equals(personalFoul.getFoulingPlayer())).count();

        this.timePlayedInMillis = Time.timePlayedInMillis(player, gameEventLog);
    }


    public static Totals sum(Totals t1, Totals t2) {
        return Totals.builder()
                .fieldGoalsAttempted(t1.fieldGoalsAttempted + t2.fieldGoalsAttempted)
                .twoPointersAttempted(t1.twoPointersAttempted + t2.twoPointersAttempted)
                .threePointersAttempted(t1.threePointersAttempted + t2.threePointersAttempted)
                .freeThrowsAttempted(t1.freeThrowsAttempted + t2.freeThrowsAttempted)
                .fieldGoalsMade(t1.fieldGoalsMade + t2.fieldGoalsMade)
                .twoPointersMade(t1.twoPointersMade + t2.twoPointersMade)
                .threePointersMade(t1.threePointersMade + t2.threePointersMade)
                .freeThrowsMade(t1.freeThrowsMade + t2.freeThrowsMade)
                .fieldGoalsMissed(t1.fieldGoalsMissed + t2.fieldGoalsMissed)
                .twoPointersMissed(t1.twoPointersMissed + t2.twoPointersMissed)
                .threePointersMissed(t1.threePointersMissed + t2.threePointersMissed)
                .freeThrowsMissed(t1.freeThrowsMissed + t2.freeThrowsMissed)
                .points(t1.points + t2.points)
                .assists(t1.assists + t2.assists)
                .rebounds(t1.rebounds + t2.rebounds)
                .blocks(t1.blocks + t2.blocks)
                .steals(t1.steals + t2.steals)
                .turnovers(t1.turnovers + t2.turnovers)
                .personalFouls(t1.personalFouls + t2.personalFouls)
                .technicalFouls(t1.technicalFouls + t2.technicalFouls)
                .timePlayedInMillis(t1.timePlayedInMillis + t2.timePlayedInMillis)
                .build();
    }
}
