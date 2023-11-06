package com.d23alex.vtbstat.payload.statistics;

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
}
