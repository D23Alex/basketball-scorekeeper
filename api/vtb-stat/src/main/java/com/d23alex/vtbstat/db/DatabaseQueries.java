package com.d23alex.vtbstat.db;

import com.d23alex.vtbstat.db.repositories.ArenaRepository;
import com.d23alex.vtbstat.db.repositories.PlayerContractRepository;
import com.d23alex.vtbstat.db.repositories.PlayerRepository;
import com.d23alex.vtbstat.db.repositories.gameevents.*;
import com.d23alex.vtbstat.entities.Arena;
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
import java.util.stream.StreamSupport;

/*
На данном этапе компонент существует главным образом для того, чтобы не прописывать множество зависимостей в контроллерах
*/
@Component
public class DatabaseQueries {
    private final GameRepository gameRepository;
    private final ArenaRepository arenaRepository;
    private final PlayerRepository playerRepository;
    private final PlayerContractRepository playerContractRepository;

    private final CoachEjectionRepository coachEjectionRepository;
    private final CoachTechnicalFoulRepository coachTechnicalFoulRepository;
    private final FieldGoalAttemptRepository fieldGoalAttemptRepository;
    private final FreeThrowAttemptRepository freeThrowAttemptRepository;
    private final PeriodEndingRepository periodEndingRepository;
    private final PeriodStartRepository periodStartRepository;
    private final LineupOccurrenceRepository lineupOccurrenceRepository;
    private final PersonalFoulRepository personalFoulRepository;
    private final PlayerEjectionRepository playerEjectionRepository;
    private final PlayerTechnicalFoulRepository playerTechnicalFoulRepository;
    private final PeriodStarterRepository periodStarterRepository;
    private final PeriodEnderRepository periodEnderRepository;
    private final AfterTimeoutCourtAppearanceRepository afterTimeoutCourtAppearanceRepository;
    private final AfterTimeoutCourtExitRepository afterTimeoutCourtExitRepository;
    private final SubstitutionCallRepository substitutionCallRepository;
    private final SubstitutionInRepository substitutionInRepository;
    private final SubstitutionOutRepository substitutionOutRepository;
    private final TimeoutRepository timeoutRepository;
    private final TurnoverRepository turnoverRepository;

    public DatabaseQueries(
            GameRepository gameRepository,
            ArenaRepository arenaRepository,
            PlayerRepository playerRepository,
            PlayerContractRepository playerContractRepository,
            FieldGoalAttemptRepository fieldGoalAttemptRepository,
            CoachEjectionRepository coachEjectionRepository,
            CoachTechnicalFoulRepository coachTechnicalFoulRepository,
            FreeThrowAttemptRepository freeThrowAttemptRepository,
            PeriodEndingRepository periodEndingRepository,
            PeriodStartRepository periodStartRepository,
            LineupOccurrenceRepository lineupOccurrenceRepository,
            PersonalFoulRepository personalFoulRepository,
            PlayerEjectionRepository playerEjectionRepository,
            PlayerTechnicalFoulRepository playerTechnicalFoulRepository,
            PeriodStarterRepository periodStarterRepository,
            PeriodEnderRepository periodEnderRepository,
            AfterTimeoutCourtAppearanceRepository afterTimeoutCourtAppearanceRepository,
            AfterTimeoutCourtExitRepository afterTimeoutCourtExitRepository,
            SubstitutionCallRepository substitutionCallRepository,
            SubstitutionInRepository substitutionInRepository,
            SubstitutionOutRepository substitutionOutRepository,
            TimeoutRepository timeoutRepository,
            TurnoverRepository turnoverRepository) {
        this.gameRepository = gameRepository;
        this.arenaRepository = arenaRepository;
        this.playerRepository = playerRepository;
        this.playerContractRepository = playerContractRepository;
        this.fieldGoalAttemptRepository = fieldGoalAttemptRepository;
        this.coachEjectionRepository = coachEjectionRepository;
        this.coachTechnicalFoulRepository = coachTechnicalFoulRepository;
        this.freeThrowAttemptRepository = freeThrowAttemptRepository;
        this.periodEndingRepository = periodEndingRepository;
        this.periodStartRepository = periodStartRepository;
        this.lineupOccurrenceRepository = lineupOccurrenceRepository;
        this.personalFoulRepository = personalFoulRepository;
        this.playerEjectionRepository = playerEjectionRepository;
        this.playerTechnicalFoulRepository = playerTechnicalFoulRepository;
        this.periodStarterRepository = periodStarterRepository;
        this.periodEnderRepository = periodEnderRepository;
        this.afterTimeoutCourtAppearanceRepository = afterTimeoutCourtAppearanceRepository;
        this.afterTimeoutCourtExitRepository = afterTimeoutCourtExitRepository;
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

    public Optional<GameEventLog> gameEventsByGameId(Long gameId) {
        Optional<Game> game = gameRepository.findById(gameId);
        if (game.isEmpty())
            return Optional.empty();
        return Optional.of(GameEventLog.builder()
                .game(game.get())
                .periodStarts(periodStartRepository.findAllByGameId(gameId))
                .periodEndings(periodEndingRepository.findAllByGameId(gameId))
                .lineupOccurrences(lineupOccurrenceRepository.findAllByGameId(gameId))
                .periodStarters(periodStarterRepository.findAllByGameId(gameId))
                .periodEnders(periodEnderRepository.findAllByGameId(gameId))
                .afterTimeoutCourtAppearances(afterTimeoutCourtAppearanceRepository.findAllByGameId(gameId))
                .afterTimeoutCourtExits(afterTimeoutCourtExitRepository.findAllByGameId(gameId))
                .coachEjections(coachEjectionRepository.findAllByGameIdOrderByMillisecondsSinceStart(gameId))
                .coachTechnicalFouls(coachTechnicalFoulRepository.findAllByGameIdOrderByMillisecondsSinceStart(gameId))
                .fieldGoalAttempts(fieldGoalAttemptRepository.findAllByGameIdOrderByMillisecondsSinceStart(gameId))
                .freeThrowAttempts(freeThrowAttemptRepository.findAllByGameIdOrderByMillisecondsSinceStart(gameId))
                .personalFouls(personalFoulRepository.findAllByGameIdOrderByMillisecondsSinceStart(gameId))
                .playerEjections(playerEjectionRepository.findAllByGameIdOrderByMillisecondsSinceStart(gameId))
                .playerTechnicalFouls(playerTechnicalFoulRepository.findAllByGameIdOrderByMillisecondsSinceStart(gameId))
                .substitutionCalls(substitutionCallRepository.findAllByGameIdOrderByMillisecondsSinceStart(gameId))
                .substitutionIns(substitutionInRepository.findAllByGameId(gameId))
                .substitutionOuts(substitutionOutRepository.findAllByGameId(gameId))
                .timeouts(timeoutRepository.findAllByGameIdOrderByMillisecondsSinceStart(gameId))
                .turnovers(turnoverRepository.findAllByGameIdOrderByMillisecondsSinceStart(gameId)).build());
    }

    public Optional<Player> playerById(Long id) {
        return playerRepository.findById(id);
    }

    public Set<Player> teamMembersByDate(Long teamId, Date date) {
        return playerContractRepository.findAllByTeamIdAndValidFromBeforeAndValidToAfter(teamId, date, date)
                .stream().map(PlayerContract::getPlayer)
                .collect(Collectors.toSet());
    }

    public Set<Arena> getAllArenas() {
        return StreamSupport.stream(arenaRepository.findAll().spliterator(), false)
                .collect(Collectors.toSet());
    }

    public Optional<Arena> getArenaById(Long id) {
        return arenaRepository.findById(id);
    }
}
