package com.d23alex.vtbstat.controller;

import com.d23alex.vtbstat.db.DatabaseQueries;
import com.d23alex.vtbstat.entity.Arena;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

@RestController
public class ArenaController {
    private final DatabaseQueries databaseQueries;

    public ArenaController(DatabaseQueries databaseQueries) {
        this.databaseQueries = databaseQueries;
    }

    @PostMapping("/api/arenas/create_arena")
    public void createArena(@RequestBody Arena arena) {
        databaseQueries.saveArena(arena);
    }

    @GetMapping("/api/arenas/{id}")
    public Arena getArenaById(@PathVariable Long id) {
        Optional<Arena> arena = databaseQueries.getArenaById(id);
        return arena.orElseThrow(() -> new NoSuchElementException("Arena with incorrect ID was provided!"));
    }


    @PutMapping("/api/arenas/update")
    public ResponseEntity<String> updateArena(@RequestBody Arena arena) {
        try {
            databaseQueries.updateArenaById(arena);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/api/arenas/{id}")
    public ResponseEntity<String> deleteArenaById(@PathVariable Long id) {
        try {
            databaseQueries.deleteArenaById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/arenas/get_all")
    public Set<Arena> getAllArenas() {
        return databaseQueries.getAllArenas();
    }
}
