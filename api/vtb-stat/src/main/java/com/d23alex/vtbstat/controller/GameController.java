package com.d23alex.vtbstat.controller;

import com.d23alex.vtbstat.repository.DatabaseQueries;
import com.d23alex.vtbstat.model.Game;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

@RestController
public class GameController {
    private final DatabaseQueries databaseQueries;

    public GameController(DatabaseQueries databaseQueries) {
        this.databaseQueries = databaseQueries;
    }

    @PostMapping("/api/games/create_game")
    public void createGame(@RequestBody Game game) {
        databaseQueries.saveGame(game);
    }

    @GetMapping("/api/games/{id}")
    public Game getGameById(@PathVariable Long id) {
        Optional<Game> game = databaseQueries.getGameById(id);
        return game.orElseThrow(() -> new NoSuchElementException("Игры с ID " + id + " не существует!"));
    }

    @PutMapping("/api/games/update")
    public ResponseEntity<String> updateGame(@RequestBody Game game) {
        try {
            databaseQueries.updateGameById(game);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/api/games/{id}")
    public ResponseEntity<String> deleteGameById(@PathVariable Long id) {
        try {
            databaseQueries.deleteGameById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/games/get_all")
    public Set<Game> getAllGames() {
        return databaseQueries.getAllGames();
    }
}
