package com.d23alex.vtbstat.db;

import com.d23alex.vtbstat.db.repositories.PlayerContractRepository;
import com.d23alex.vtbstat.db.repositories.PlayerRepository;
import com.d23alex.vtbstat.db.repositories.gameevents.*;
import com.d23alex.vtbstat.entities.Game;
import com.d23alex.vtbstat.entities.Player;
import com.d23alex.vtbstat.entities.PlayerContract;
import com.d23alex.vtbstat.game.GameEventLog;
import com.d23alex.vtbstat.db.repositories.GameRepository;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/*
На данном этапе компонент существует главным образом для того, чтобы не прописывать множество зависимостей в контроллерах
*/
@Component
public class DatabaseQueries {
    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;
    private final PlayerContractRepository playerContractRepository;

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
            PlayerRepository playerRepository,
            PlayerContractRepository playerContractRepository,
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
        this.playerRepository = playerRepository;
        this.playerContractRepository = playerContractRepository;
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

    public Optional<Game> gameById(Long id) {
        return gameRepository.findById(id);
    }

    public GameEventLog gameEventsByGameId(Long gameId) {
        return new GameEventLog(
                gameStartRepository.findByGameId(gameId),
                gameEndingRepository.findByGameId(gameId),
                lineupOccurrenceRepository.findAllByGameId(gameId),
                startingLineupOccurrenceRepository.findAllByGameId(gameId),
                coachEjectionRepository.findAllByGameIdOrderByMillisecondsSinceStart(gameId),
                coachTechnicalFoulRepository.findAllByGameIdOrderByMillisecondsSinceStart(gameId),
                fieldGoalAttemptRepository.findAllByGameIdOrderByMillisecondsSinceStart(gameId),
                freeThrowAttemptRepository.findAllByGameIdOrderByMillisecondsSinceStart(gameId),
                personalFoulRepository.findAllByGameIdOrderByMillisecondsSinceStart(gameId),
                playerEjectionRepository.findAllByGameIdOrderByMillisecondsSinceStart(gameId),
                playerTechnicalFoulRepository.findAllByGameIdOrderByMillisecondsSinceStart(gameId),
                reboundRepository.findAllByGameIdOrderByMillisecondsSinceStart(gameId),
                substitutionCallRepository.findAllByGameIdOrderByMillisecondsSinceStart(gameId),
                substitutionInRepository.findAllByGameId(gameId),
                substitutionOutRepository.findAllByGameId(gameId),
                timeoutRepository.findAllByGameIdOrderByMillisecondsSinceStart(gameId),
                turnoverRepository.findAllByGameIdOrderByMillisecondsSinceStart(gameId));
    }

    public Optional<Player> playerById(Long id) {
        return playerRepository.findById(id);
    }


    public Set<Player> teamMembersByDate(Long teamId, Date date) {
        return playerContractRepository.findAllByTeamIdAndValidFromBeforeAndValidToAfter(teamId, date, date)
                .stream().map(PlayerContract::getPlayer)
                .collect(Collectors.toSet());
    }
}
