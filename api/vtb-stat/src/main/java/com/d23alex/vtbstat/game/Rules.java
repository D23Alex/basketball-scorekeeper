package com.d23alex.vtbstat.game;

public class Rules {
    public enum EjectionCause {
        FOULS,
        UNSPORTSMANLIKE_BEHAVIOR,
        OTHER
    }

    public enum TurnoverCause {
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
