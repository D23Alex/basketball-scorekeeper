package com.d23alex.vtbstat.controller;

import com.d23alex.vtbstat.repository.DatabaseQueries;
import com.d23alex.vtbstat.model.CoachContract;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

@CrossOrigin
@RestController
public class CoachContractController {

    private final DatabaseQueries databaseQueries;

    public CoachContractController(DatabaseQueries databaseQueries) {
        this.databaseQueries = databaseQueries;
    }

    @PostMapping("/api/coach_contracts/create")
    public void createCoachContract(@RequestBody CoachContract coachContract) {
        databaseQueries.saveCoachContract(coachContract);
    }

    @GetMapping("/api/coach_contracts/{id}")
    public CoachContract getCoachContractById(@PathVariable Long id) {
        Optional<CoachContract> coachContract = databaseQueries.getCoachContractById(id);
        return coachContract.orElseThrow(() -> new NoSuchElementException("Контракта с ID " + id + " не существует!"));
    }

    @PutMapping("/api/coach_contracts/update")
    public ResponseEntity<String> updateCoachContract(@RequestBody CoachContract coachContract) {
        try {
            databaseQueries.updateCoachContractById(coachContract);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/api/coach_contracts/{id}")
    public ResponseEntity<String> deleteCoachContractById(@PathVariable Long id) {
        try {
            databaseQueries.deleteCoachContractById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/coach_contracts/get_all")
    public Set<CoachContract> getAllCoachContracts() {
        return databaseQueries.getAllCoachContracts();
    }
}
