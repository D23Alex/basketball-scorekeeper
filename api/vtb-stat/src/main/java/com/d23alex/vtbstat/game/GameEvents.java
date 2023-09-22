package com.d23alex.vtbstat.game;

import com.d23alex.vtbstat.entities.gameevents.*;


//TODO: would GameLog be a better name?
public record GameEvents(
    GameStart gameStart,
    GameEnding gameEnding,
    Iterable<LineupOccurrence> lineupOccurrences,
    Iterable<StartingLineupOccurrence> startingLineupOccurrences,
    Iterable<CoachEjection> coachEjections,
    Iterable<CoachTechnicalFoul> coachTechnicalFouls,
    Iterable<FieldGoalAttempt> fieldGoalAttempts,
    Iterable<FreeThrowAttempt> freeThrowAttempts,
    Iterable<PersonalFoul> personalFouls,
    Iterable<PlayerEjection> playerEjections,
    Iterable<PlayerTechnicalFoul> playerTechnicalFouls,
    Iterable<Rebound> rebounds,
    Iterable<SubstitutionCall> substitutionCalls,
    Iterable<SubstitutionIn> substitutionIns,
    Iterable<SubstitutionOut> substitutionOuts,
    Iterable<Timeout> timeouts,
    Iterable<Turnover> turnovers
) {}
