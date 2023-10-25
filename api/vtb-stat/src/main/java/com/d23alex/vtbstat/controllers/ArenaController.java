package com.d23alex.vtbstat.controllers;

import com.d23alex.vtbstat.db.DatabaseQueries;
import com.d23alex.vtbstat.entities.Arena;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

@RestController
public class ArenaController {
    private final DatabaseQueries databaseQueries;

    public ArenaController(DatabaseQueries databaseQueries) {
        this.databaseQueries = databaseQueries;
    }

    @GetMapping("/api/arenas/{id}")
    public Arena getArenaById(@PathVariable Long id) {
        Optional<Arena> arena = databaseQueries.getArenaById(id);
        return arena.orElseThrow(() -> new NoSuchElementException("Arena with incorrect ID was provided!"));
    }

    //FIXME: change path!
    @GetMapping("/api/get_all_arenas")
    public Set<Arena> getAllArenas() {
        return databaseQueries.getAllArenas();
    }
}
