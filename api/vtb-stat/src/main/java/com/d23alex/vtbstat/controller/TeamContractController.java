package com.d23alex.vtbstat.controller;

import com.d23alex.vtbstat.repository.DatabaseQueries;
import com.d23alex.vtbstat.model.TeamContract;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

@RestController
public class TeamContractController {
    private final DatabaseQueries databaseQueries;

    public TeamContractController(DatabaseQueries databaseQueries) {
        this.databaseQueries = databaseQueries;
    }

    @PostMapping("/api/team_contracts/create")
    public void createTeamContract(@RequestBody TeamContract teamContract) {
        databaseQueries.saveTeamContract(teamContract);
    }

    @GetMapping("/api/team_contracts/{id}")
    public TeamContract getTeamContractById(@PathVariable Long id) {
        Optional<TeamContract> teamContract = databaseQueries.getTeamContractById(id);
        return teamContract.orElseThrow(() -> new NoSuchElementException("Контракта с ID " + id + " не существует!"));
    }

    @PutMapping("/api/team_contracts/update")
    public ResponseEntity<String> updateTeamContract(@RequestBody TeamContract teamContract) {
        try {
            databaseQueries.updateTeamContractById(teamContract);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/api/team_contracts/{id}")
    public ResponseEntity<String> deleteTeamContractById(@PathVariable Long id) {
        try {
            databaseQueries.deleteTeamContractById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/team_contracts/get_all")
    public Set<TeamContract> getAllTeamContracts() {
        return databaseQueries.getAllTeamContracts();
    }
}