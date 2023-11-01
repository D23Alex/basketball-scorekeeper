package com.d23alex.vtbstat.db;

import com.d23alex.vtbstat.db.repository.*;
import com.d23alex.vtbstat.db.repository.gameevents.*;
import com.d23alex.vtbstat.entity.*;
import com.d23alex.vtbstat.game.GameEventLog;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.NoSuchElementException;
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
    private final CoachRepository coachRepository;
    private final CoachContractRepository coachContractRepository;
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
            CoachRepository coachRepository,
            CoachContractRepository coachContractRepository,
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
        this.coachRepository = coachRepository;
        this.coachContractRepository = coachContractRepository;
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

    public Set<Player> teamMembersByDate(Long teamId, Date date) {
        return playerContractRepository.findAllByTeamIdAndValidFromBeforeAndValidToAfter(teamId, date, date)
                .stream().map(PlayerContract::getPlayer)
                .collect(Collectors.toSet());
    }

    public Set<Arena> getAllArenas() {
        return StreamSupport.stream(arenaRepository.findAll().spliterator(), false)
                .collect(Collectors.toSet());
    }

    public void saveArena(Arena arena) {
        arenaRepository.save(arena);
    }

    public Optional<Arena> getArenaById(Long id) {
        return arenaRepository.findById(id);
    }

    public void updateArenaById(Arena updatedArena) {
        Optional<Arena> optionalArena = arenaRepository.findById(updatedArena.getId());

        if (optionalArena.isPresent()) {
            Arena arena = optionalArena.get();
            arena.setName(updatedArena.getName());
            arena.setAddress(updatedArena.getAddress());
            arenaRepository.save(arena);
        } else {
            throw new NoSuchElementException("Арены с ID " + updatedArena.getId() + " не существует!");
        }
    }

    public void deleteArenaById(Long id) {
        if (arenaRepository.existsById(id)) {
            arenaRepository.deleteById(id);
        } else {
            throw new NoSuchElementException("Арены с ID " + id + " не существует!");
        }
    }

    public void saveCoach(Coach coach) {
        coachRepository.save(coach);
    }

    public Optional<Coach> getCoachById(Long id) {
        return coachRepository.findById(id);
    }

    public void updateCoachById(Coach updatedCoach) {
        Optional<Coach> optionalCoach = coachRepository.findById(updatedCoach.getId());

        if (optionalCoach.isPresent()) {
            Coach coach = optionalCoach.get();
            coach.setFirstName(updatedCoach.getFirstName());
            coach.setLastName(updatedCoach.getLastName());
            coach.setDateOfBirth(updatedCoach.getDateOfBirth());
            coach.setDescription(updatedCoach.getDescription());
        } else {
            throw new NoSuchElementException("Тренера с ID " + updatedCoach.getId() + " не существует!");
        }
    }

    public void deleteCoachById(Long id) {
        if (coachRepository.existsById(id)) {
            coachRepository.deleteById(id);
        } else {
            throw new NoSuchElementException("Тренера с ID " + id + " не существует!");
        }
    }

    public Set<Coach> getAllCoaches() {
        return StreamSupport.stream(coachRepository.findAll().spliterator(), false)
                .collect(Collectors.toSet());
    }

    public void saveCoachContract(CoachContract coachContract) {
        coachContractRepository.save(coachContract);
    }

    public Optional<CoachContract> getCoachContractById(Long id) {
        return coachContractRepository.findById(id);
    }

    public void updateCoachContractById(CoachContract updatedCoachContract) {
        Optional<CoachContract> optionalCoachContract = coachContractRepository.findById(updatedCoachContract.getId());

        if (optionalCoachContract.isPresent()) {
            CoachContract coachContract = optionalCoachContract.get();
            coachContract.setCoach(updatedCoachContract.getCoach());
            coachContract.setTeam(updatedCoachContract.getTeam());
            coachContract.setValidFrom(updatedCoachContract.getValidFrom());
            coachContract.setValidTo(updatedCoachContract.getValidTo());
        } else {
            throw new NoSuchElementException("Контракта с ID " + updatedCoachContract.getId() + " не существует!");
        }
    }

    public void deleteCoachContractById(Long id) {
        if (coachContractRepository.existsById(id)) {
            coachContractRepository.deleteById(id);
        } else {
            throw new NoSuchElementException("Контракта с ID " + id + " не существует!");
        }
    }

    public Set<CoachContract> getAllCoachContracts() {
        return StreamSupport.stream(coachContractRepository.findAll().spliterator(), false)
                .collect(Collectors.toSet());
    }

    public void saveGame(Game game) {
        gameRepository.save(game);
    }

    public Optional<Game> getGameById(Long id) {
        return gameRepository.findById(id);
    }

    public void updateGameById(Game updatedGame) {
        Optional<Game> optionalGame = gameRepository.findById(updatedGame.getId());

        if (optionalGame.isPresent()) {
            Game game = optionalGame.get();
            game.setArena(updatedGame.getArena());
            game.setTeam1(updatedGame.getTeam1());
            game.setTeam2(updatedGame.getTeam2());
            game.setHomeTeam(updatedGame.getHomeTeam());
            game.setScheduledStartTime(updatedGame.getScheduledStartTime());
        } else {
            throw new NoSuchElementException("Игры с ID " + updatedGame.getId() + " не существует!");
        }
    }

    public void deleteGameById(Long id) {
        if (gameRepository.existsById(id)) {
            gameRepository.deleteById(id);
        } else {
            throw new NoSuchElementException("Игры с ID " + id + " не существует!");
        }
    }

    public Set<Game> getAllGames() {
        return StreamSupport.stream(gameRepository.findAll().spliterator(), false)
                .collect(Collectors.toSet());
    }

    public void savePlayer(Player player) {
        playerRepository.save(player);
    }

    public Optional<Player> getPlayerById(Long id) {
        return playerRepository.findById(id);
    }

    public void updatePlayerById(Player updatedPlayer) {
        Optional<Player> optionalPlayer = playerRepository.findById(updatedPlayer.getId());

        if (optionalPlayer.isPresent()) {
            Player player = optionalPlayer.get();
            player.setFirstName(updatedPlayer.getFirstName());
            player.setLastName(updatedPlayer.getLastName());
            player.setDateOfBirth(updatedPlayer.getDateOfBirth());
            player.setProfileImagePath(updatedPlayer.getProfileImagePath());
            player.setDescription(updatedPlayer.getDescription());
            player.setPosition(updatedPlayer.getPosition());
        } else {
            throw new NoSuchElementException("Игрока с ID " + updatedPlayer.getId() + " не существует!");
        }
    }

    public void deletePlayerById(Long id) {
        if (playerRepository.existsById(id)) {
            playerRepository.deleteById(id);
        } else {
            throw new NoSuchElementException("Игрока с ID " + id + " не существует!");
        }
    }

    public Set<Player> getAllPlayers() {
        return StreamSupport.stream(playerRepository.findAll().spliterator(), false)
                .collect(Collectors.toSet());
    }
}
