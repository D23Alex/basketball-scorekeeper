package com.d23alex.vtbstat.db;

import com.d23alex.vtbstat.db.repositories.gameevents.*;
import com.d23alex.vtbstat.game.GameEvents;
import com.d23alex.vtbstat.db.repositories.GameRepository;
import org.springframework.stereotype.Component;

/*
На данном этапе компонент существует главным образом для того, чтобы не прописывать множество зависимостей в контроллерах
*/
@Component
public class DatabaseQueries {
    private final GameRepository gameRepository;

    private final CoachEjectionRepository coachEjectionRepository;
    private final CoachTechnicalFoulRepository coachTechnicalFoulRepository;
    private final FieldGoalAttemptRepository fieldGoalAttemptRepository;
    private final FreeThrowAttemptRepository freeThrowAttemptRepository;
    private final GameEndingRepository gameEndingRepository;
    private final GameStartRepository gameStartRepository;
    private final LineupOccurrenceRepository lineupOccurrenceRepository;
    private final PersonalFoulRepository personalFoulRepository;
    private final PlayerEjectionRepository playerEjectionRepository;
    private final PlayerTechnicalFoulRepository playerTechnicalFoulRepository;
    private final ReboundRepository reboundRepository;
    private final StartingLineupOccurrenceRepository startingLineupOccurrenceRepository;
    private final SubstitutionCallRepository substitutionCallRepository;
    private final SubstitutionInRepository substitutionInRepository;
    private final SubstitutionOutRepository substitutionOutRepository;
    private final TimeoutRepository timeoutRepository;
    private final TurnoverRepository turnoverRepository;

    public DatabaseQueries(
            GameRepository gameRepository,
            FieldGoalAttemptRepository fieldGoalAttemptRepository,
            CoachEjectionRepository coachEjectionRepository,
            CoachTechnicalFoulRepository coachTechnicalFoulRepository,
            FreeThrowAttemptRepository freeThrowAttemptRepository,
            GameEndingRepository gameEndingRepository,
            GameStartRepository gameStartRepository,
            LineupOccurrenceRepository lineupOccurrenceRepository,
            PersonalFoulRepository personalFoulRepository,
            PlayerEjectionRepository playerEjectionRepository,
            PlayerTechnicalFoulRepository playerTechnicalFoulRepository,
            ReboundRepository reboundRepository,
            StartingLineupOccurrenceRepository startingLineupOccurrenceRepository,
            SubstitutionCallRepository substitutionCallRepository,
            SubstitutionInRepository substitutionInRepository,
            SubstitutionOutRepository substitutionOutRepository,
            TimeoutRepository timeoutRepository,
            TurnoverRepository turnoverRepository) {
        this.gameRepository = gameRepository;
        this.fieldGoalAttemptRepository = fieldGoalAttemptRepository;
        this.coachEjectionRepository = coachEjectionRepository;
        this.coachTechnicalFoulRepository = coachTechnicalFoulRepository;
        this.freeThrowAttemptRepository = freeThrowAttemptRepository;
        this.gameEndingRepository = gameEndingRepository;
        this.gameStartRepository = gameStartRepository;
        this.lineupOccurrenceRepository = lineupOccurrenceRepository;
        this.personalFoulRepository = personalFoulRepository;
        this.playerEjectionRepository = playerEjectionRepository;
        this.playerTechnicalFoulRepository = playerTechnicalFoulRepository;
        this.reboundRepository = reboundRepository;
        this.startingLineupOccurrenceRepository = startingLineupOccurrenceRepository;
        this.substitutionCallRepository = substitutionCallRepository;
        this.substitutionInRepository = substitutionInRepository;
        this.substitutionOutRepository = substitutionOutRepository;
        this.timeoutRepository = timeoutRepository;
        this.turnoverRepository = turnoverRepository;
    }

    public boolean gameExistsById(Long id) {
        return gameRepository.existsById(id);
    }

    public GameEvents gameEventsByGameId(Long gameId) {
        return new GameEvents(
                gameStartRepository.findByGameId(gameId),
                gameEndingRepository.findByGameId(gameId),
                lineupOccurrenceRepository.findAllByGameId(gameId),
                startingLineupOccurrenceRepository.findAllByGameId(gameId),
                coachEjectionRepository.findAllByGameId(gameId),
                coachTechnicalFoulRepository.findAllByGameId(gameId),
                fieldGoalAttemptRepository.findAllByGameId(gameId),
                freeThrowAttemptRepository.findAllByGameId(gameId),
                personalFoulRepository.findAllByGameId(gameId),
                playerEjectionRepository.findAllByGameId(gameId),
                playerTechnicalFoulRepository.findAllByGameId(gameId),
                reboundRepository.findAllByGameId(gameId),
                substitutionCallRepository.findAllByGameId(gameId),
                substitutionInRepository.findAllByGameId(gameId),
                substitutionOutRepository.findAllByGameId(gameId),
                timeoutRepository.findAllByGameId(gameId),
                turnoverRepository.findAllByGameId(gameId));
    }
}
