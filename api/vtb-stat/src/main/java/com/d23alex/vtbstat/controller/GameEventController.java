package com.d23alex.vtbstat.controller;

import com.d23alex.vtbstat.model.gameevents.*;
import com.d23alex.vtbstat.repository.DatabaseQueries;
import com.d23alex.vtbstat.payload.statistics.GameEventLog;
import com.d23alex.vtbstat.repository.gameevents.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;

@CrossOrigin
@RestController
public class GameEventController {
    private final DatabaseQueries databaseQueries;
    private final FieldGoalAttemptRepository fieldGoalAttemptRepository;
    private final FreeThrowAttemptRepository freeThrowAttemptRepository;

    private final PersonalFoulRepository personalFoulRepository;

    private final PlayerTechnicalFoulRepository playerTechnicalFoulRepository;

    private final PlayerEjectionRepository playerEjectionRepository;

    private final TurnoverRepository turnoverRepository;


    public GameEventController(DatabaseQueries databaseQueries,
                               FieldGoalAttemptRepository fieldGoalAttemptRepository,
                               FreeThrowAttemptRepository freeThrowAttemptRepository,
                               PersonalFoulRepository personalFoulRepository,
                               PlayerTechnicalFoulRepository playerTechnicalFoulRepository,
                               PlayerEjectionRepository playerEjectionRepository,
                               TurnoverRepository turnoverRepository) {
        this.databaseQueries = databaseQueries;
        this.fieldGoalAttemptRepository = fieldGoalAttemptRepository;
        this.freeThrowAttemptRepository = freeThrowAttemptRepository;
        this.personalFoulRepository = personalFoulRepository;
        this.playerTechnicalFoulRepository = playerTechnicalFoulRepository;
        this.playerEjectionRepository = playerEjectionRepository;
        this.turnoverRepository = turnoverRepository;
    }

    @GetMapping("/api/events/game/{gameId}")
    Optional<GameEventLog> gameEventsByGameId(@PathVariable long gameId) {
        if (databaseQueries.isGameExistsById(gameId))
            return databaseQueries.gameEventsByGameId(gameId);
        return Optional.empty();
    }

    @GetMapping("/api/events/field-goal-attempt/{id}")
    Optional<FieldGoalAttempt> fieldGoalAttemptById(@PathVariable Long id) {
        return fieldGoalAttemptRepository.findById(id);
    }

    @PostMapping("/api/events/field-goal-attempt")
    FieldGoalAttempt createFieldGoalAttempt(@RequestBody FieldGoalAttempt fieldGoalAttempt) {
        return fieldGoalAttemptRepository.save(fieldGoalAttempt);
    }

    @DeleteMapping("/api/events/field-goal-attempt/{id}")
    public ResponseEntity<String> deleteFieldGoalAttemptById(@PathVariable Long id) {
        try {
            fieldGoalAttemptRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/events/free-throw-attempt/{id}")
    Optional<FreeThrowAttempt> freeThrowAttemptById(@PathVariable Long id) {
        return freeThrowAttemptRepository.findById(id);
    }

    @PostMapping("/api/events/free-throw-attempt")
    FreeThrowAttempt createFreeThrowAttempt(@RequestBody FreeThrowAttempt freeThrowAttempt) {
        return freeThrowAttemptRepository.save(freeThrowAttempt);
    }

    @DeleteMapping("/api/events/free-throw-attempt/{id}")
    public ResponseEntity<String> deleteFreeThrowAttemptById(@PathVariable Long id) {
        try {
            freeThrowAttemptRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/events/personal-foul/{id}")
    Optional<PersonalFoul> personalFoulById(@PathVariable Long id) {
        return personalFoulRepository.findById(id);
    }

    @PostMapping("/api/events/personal-foul")
    PersonalFoul createPersonalFoul(@RequestBody PersonalFoul personalFoul) {
        return personalFoulRepository.save(personalFoul);
    }

    @DeleteMapping("/api/events/personal-foul/{id}")
    public ResponseEntity<String> deletePersonalFoulById(@PathVariable Long id) {
        try {
            personalFoulRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/events/player-technical-foul/{id}")
    Optional<PlayerTechnicalFoul> playerTechnicalFoulById(@PathVariable Long id) {
        return playerTechnicalFoulRepository.findById(id);
    }

    @PostMapping("/api/events/player-technical-foul")
    PlayerTechnicalFoul createPlayerTechnicalFoul(@RequestBody PlayerTechnicalFoul playerTechnicalFoul) {
        return playerTechnicalFoulRepository.save(playerTechnicalFoul);
    }

    @DeleteMapping("/api/events/player-technical-foul/{id}")
    public ResponseEntity<String> deletePlayerTechnicalFoulById(@PathVariable Long id) {
        try {
            playerTechnicalFoulRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/events/player-ejection/{id}")
    Optional<PlayerEjection> playerEjectionById(@PathVariable Long id) {
        return playerEjectionRepository.findById(id);
    }

    @PostMapping("/api/events/player-ejection")
    PlayerEjection createPlayerEjection(@RequestBody PlayerEjection playerEjection) {
        return playerEjectionRepository.save(playerEjection);
    }

    @DeleteMapping("/api/events/player-ejection/{id}")
    public ResponseEntity<String> deletePlayerEjectionById(@PathVariable Long id) {
        try {
            playerEjectionRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }





    @GetMapping("/api/events/turnover/{id}")
    Optional<Turnover> turnoverById(@PathVariable Long id) {
        return turnoverRepository.findById(id);
    }

    @PostMapping("/api/events/turnover")
    Turnover createTurnover(@RequestBody Turnover turnover) {
        return turnoverRepository.save(turnover);
    }

    @DeleteMapping("/api/events/turnover/{id}")
    public ResponseEntity<String> deleteTurnoverById(@PathVariable Long id) {
        try {
            turnoverRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
