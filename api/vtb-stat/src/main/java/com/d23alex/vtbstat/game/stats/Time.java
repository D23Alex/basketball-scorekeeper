package com.d23alex.vtbstat.game.stats;

import com.d23alex.vtbstat.Util;
import com.d23alex.vtbstat.entity.Player;
import com.d23alex.vtbstat.entity.gameevents.GameClockTimestamped;
import com.d23alex.vtbstat.game.GameEventLog;
import com.d23alex.vtbstat.game.Rules;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Time {
    public static long timePlayedInMillis(Player player, GameEventLog gameEventLog) {
        var periodStartingTimestamps = gameEventLog.getPeriodStarters()
                .stream().filter(periodStarter -> player.equals(periodStarter.getPlayer()))
                .map(periodStarter -> millisPassedByPeriodStart(periodStarter.getPeriod())).toList();

        var subInTimestamps = gameEventLog.getSubstitutionIns()
                .stream().filter(subIn -> player.equals(subIn.getPlayer()))
                .map(subIn -> subIn.getSubstitutionCall().getMillisecondsSinceStart()).toList();

        var afterTimeOutCourtAppearanceTimestamps = gameEventLog.getAfterTimeoutCourtAppearances()
                .stream().filter(appearance -> player.equals(appearance.getPlayer()))
                .map(appearance -> appearance.getTimeout().getMillisecondsSinceStart()).toList();

        var periodEndingTimestamps = gameEventLog.getPeriodEnders()
                .stream().filter(periodEnder -> player.equals(periodEnder.getPlayer()))
                .map(periodEnder -> millisPassedByPeriodStart(periodEnder.getPeriod())).toList();

        var subOutTimestamps = gameEventLog.getSubstitutionOuts()
                .stream().filter(subOut -> player.equals(subOut.getPlayer()))
                .map(subOut -> subOut.getSubstitutionCall().getMillisecondsSinceStart()).toList();

        var afterTimeOutCourtExitTimestamps = gameEventLog.getAfterTimeoutCourtExits()
                .stream().filter(exit -> player.equals(exit.getPlayer()))
                .map(exit -> exit.getTimeout().getMillisecondsSinceStart()).toList();

        var allEntranceTimestamps = Util.concatMultiple(periodStartingTimestamps,
                subInTimestamps, afterTimeOutCourtAppearanceTimestamps);

        var allExitTimestamps = Util.concatMultiple(periodEndingTimestamps,
                subOutTimestamps, afterTimeOutCourtExitTimestamps);

        var playerStillOnCourt = allEntranceTimestamps.size() > allExitTimestamps.size();
        return Util.sum(allExitTimestamps) + (playerStillOnCourt ? lastEventTimestamp(gameEventLog) : 0)
                - Util.sum(allEntranceTimestamps);

    }

    private static long lastEventTimestamp(GameEventLog gameEventLog) {
        var allEvents = Util.concatMultiple(
                gameEventLog.getCoachEjections(),
                gameEventLog.getCoachTechnicalFouls(),
                gameEventLog.getFieldGoalAttempts(),
                gameEventLog.getFreeThrowAttempts(),
                gameEventLog.getPersonalFouls(),
                gameEventLog.getPlayerEjections(),
                gameEventLog.getPlayerTechnicalFouls(),
                gameEventLog.getSubstitutionCalls(),
                gameEventLog.getTimeouts(),
                gameEventLog.getTurnovers()
        );
        var lastEvent = latest(allEvents);
        return lastEvent.isEmpty() ? 0 : lastEvent.get().getMillisecondsSinceStart();
    }

    private static Optional<GameClockTimestamped> latest(List<GameClockTimestamped> events) {
        return events.stream().max(Comparator.comparingLong(GameClockTimestamped::getMillisecondsSinceStart));
    }

    private static long millisPassedByPeriodStart(long period) {
        return millisInPeriods(period - 1);
    }

    private static long millisInPeriods(long periods) {
        if (periods < 0)
            return 0;
        if (periods <= 4)
            return periods * Rules.gameQuarterLengthInMillis;
        return 4 * Rules.gameQuarterLengthInMillis + (periods - 4) * Rules.overtimeLengthInMillis;
    }
}
