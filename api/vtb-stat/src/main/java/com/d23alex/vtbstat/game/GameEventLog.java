package com.d23alex.vtbstat.game;

import com.d23alex.vtbstat.entities.Game;
import com.d23alex.vtbstat.entities.gameevents.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;
import java.util.Set;


@Data
@Getter
@Builder
public class GameEventLog {
    private Game game;
    private List<PeriodStart> periodStarts;
    private List<PeriodEnding> periodEndings;
    private Set<LineupOccurrence> lineupOccurrences;
    private Set<PeriodStarter> periodStarters;
    private Set<PeriodEnder> periodEnders;
    private Set<AfterTimeoutCourtAppearance> afterTimeoutCourtAppearances;
    private Set<AfterTimeoutCourtExit> afterTimeoutCourtExits;
    private List<CoachEjection> coachEjections;
    private List<CoachTechnicalFoul> coachTechnicalFouls;
    private List<FieldGoalAttempt> fieldGoalAttempts;
    private List<FreeThrowAttempt> freeThrowAttempts;
    private List<PersonalFoul> personalFouls;
    private List<PlayerEjection> playerEjections;
    private List<PlayerTechnicalFoul> playerTechnicalFouls;
    private List<SubstitutionCall> substitutionCalls;
    private Set<SubstitutionIn> substitutionIns;
    private Set<SubstitutionOut> substitutionOuts;
    private List<Timeout> timeouts;
    private List<Turnover> turnovers;
}
