package com.d23alex.vtbstat.controller;

import com.d23alex.vtbstat.LeagueSchedule;
import com.d23alex.vtbstat.repository.DatabaseQueries;
import com.d23alex.vtbstat.model.PlayerContract;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

@RestController
public class PlayerContractController {
    private final DatabaseQueries databaseQueries;

    public PlayerContractController(DatabaseQueries databaseQueries) {
        this.databaseQueries = databaseQueries;
    }

    @PostMapping("/api/player-contracts/create")
    public void createPlayerContract(@RequestBody PlayerContract playerContract) {
        databaseQueries.savePlayerContract(playerContract);
    }

    @GetMapping("/api/player-contracts/{id}")
    public PlayerContract getPlayerContractById(@PathVariable Long id) {
        Optional<PlayerContract> playerContract = databaseQueries.getPlayerContractById(id);
        return playerContract.orElseThrow(() -> new NoSuchElementException("Контракта с ID " + id + " не существует!"));
    }

    @PutMapping("/api/player-contracts/update")
    public ResponseEntity<String> updatePlayerContract(@RequestBody PlayerContract playerContract) {
        try {
            databaseQueries.updatePlayerContractById(playerContract);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/api/player-contracts/{id}")
    public ResponseEntity<String> deletePlayerContractById(@PathVariable Long id) {
        try {
            databaseQueries.deletePlayerContractById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/player-contracts/get-all")
    public Set<PlayerContract> getAllPlayerContracts() {
        return databaseQueries.getAllPlayerContracts();
    }

    @GetMapping("/api/player-contracts/get-all-by-player/{playerId}")
    public Set<PlayerContract> getAllPlayerContractsByPlayer(@PathVariable Long playerId) {
        return databaseQueries.getAllPlayerContractsByPlayerId(playerId);
    }

    @GetMapping("/api/player-contracts/{playerId}/last-in-season/{season}")
    public PlayerContract getLastPlayerContractInSeason(@PathVariable Long playerId, @PathVariable Integer season) {
        return databaseQueries.allPlayerContractsValidWithinTime(
                playerId, LeagueSchedule.seasonStart.get(season), LeagueSchedule.seasonEnd.get(season))
                .stream().max(Comparator.comparing(PlayerContract::getValidFrom)).orElse(null);
    }
}
