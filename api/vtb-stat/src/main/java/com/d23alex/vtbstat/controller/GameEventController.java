package com.d23alex.vtbstat.controller;

import com.d23alex.vtbstat.model.gameevents.FieldGoalAttempt;
import com.d23alex.vtbstat.repository.DatabaseQueries;
import com.d23alex.vtbstat.payload.statistics.GameEventLog;
import com.d23alex.vtbstat.repository.gameevents.FieldGoalAttemptRepository;
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


    public GameEventController(DatabaseQueries databaseQueries,
                               FieldGoalAttemptRepository fieldGoalAttemptRepository) {
        this.databaseQueries = databaseQueries;
        this.fieldGoalAttemptRepository = fieldGoalAttemptRepository;
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
}
