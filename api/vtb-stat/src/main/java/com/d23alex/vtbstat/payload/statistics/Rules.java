package com.d23alex.vtbstat.payload.statistics;

import com.d23alex.vtbstat.model.Game;
import com.d23alex.vtbstat.model.gameevents.PeriodEnding;
import com.d23alex.vtbstat.model.gameevents.PeriodStart;

import java.util.List;

public class Rules {
    public static long gameQuarterLengthInMillis = 10 * 60 * 1000;
    public static long overtimeLengthInMillis = 5 * 60 * 1000;

    public enum EjectionCause {
        FOULS,
        UNSPORTSMANLIKE_BEHAVIOR,
        OTHER
    }

    public enum TurnoverCause {
        STOLEN_BY_OPPONENT,
        FIVE_SECOND_VIOLATION,
        EIGHT_SECOND_VIOLATION,
        TWENTY_FOUR_SECOND_VIOLATION,
        ZONE_VIOLATION,
        OUT_OF_BOUNDS,
        TRAVEL,
        LEG_PLAY,
        OTHER
    }

    public enum GameStatus {
        UNKNOWN,
        NOT_STARTED,
        IN_PROGRESS,
        FINISHED
    }

    public static GameStatus calculateGameStatus(long team1Score, long team2Score, int periodStarts, int periodEndings) {
        if (periodStarts == 0)
            return GameStatus.NOT_STARTED;
        if (periodStarts < 4 || team1Score == team2Score || periodStarts > periodEndings)
            return GameStatus.IN_PROGRESS;
        return GameStatus.FINISHED;
    }
}
