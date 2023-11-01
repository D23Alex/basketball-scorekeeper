package com.d23alex.vtbstat.controller;

import com.d23alex.vtbstat.db.DatabaseQueries;
import com.d23alex.vtbstat.entity.PlayerContract;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

@RestController
public class PlayerContractController {
    private final DatabaseQueries databaseQueries;

    public PlayerContractController(DatabaseQueries databaseQueries) {
        this.databaseQueries = databaseQueries;
    }

    @PostMapping("/api/player_contracts/create")
    public void createPlayerContract(@RequestBody PlayerContract playerContract) {
        databaseQueries.savePlayerContract(playerContract);
    }

    @GetMapping("/api/player_contracts/{id}")
    public PlayerContract getPlayerContractById(@PathVariable Long id) {
        Optional<PlayerContract> playerContract = databaseQueries.getPlayerContractById(id);
        return playerContract.orElseThrow(() -> new NoSuchElementException("Контракта с ID " + id + " не существует!"));
    }

    @PutMapping("/api/player_contracts/update")
    public ResponseEntity<String> updatePlayerContract(@RequestBody PlayerContract playerContract) {
        try {
            databaseQueries.updatePlayerContractById(playerContract);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/api/player_contracts/{id}")
    public ResponseEntity<String> deletePlayerContractById(@PathVariable Long id) {
        try {
            databaseQueries.deletePlayerContractById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/player_contracts/get_all")
    public Set<PlayerContract> getAllPlayerContracts() {
        return databaseQueries.getAllPlayerContracts();
    }
}
