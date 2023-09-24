package com.d23alex.vtbstat.game;

import com.d23alex.vtbstat.entities.gameevents.*;

import java.util.List;
import java.util.Set;


public record GameEventLog(
    GameStart gameStart,
    GameEnding gameEnding,
    Set<LineupOccurrence> lineupOccurrences,
    Set<StartingLineupOccurrence> startingLineupOccurrences,
    List<CoachEjection> coachEjections,
    List<CoachTechnicalFoul> coachTechnicalFouls,
    List<FieldGoalAttempt> fieldGoalAttempts,
    List<FreeThrowAttempt> freeThrowAttempts,
    List<PersonalFoul> personalFouls,
    List<PlayerEjection> playerEjections,
    List<PlayerTechnicalFoul> playerTechnicalFouls,
    List<SubstitutionCall> substitutionCalls,
    Set<SubstitutionIn> substitutionIns,
    Set<SubstitutionOut> substitutionOuts,
    List<Timeout> timeouts,
    List<Turnover> turnovers
) {}
