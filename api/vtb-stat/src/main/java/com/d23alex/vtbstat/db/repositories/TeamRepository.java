package com.d23alex.vtbstat.db.repositories;

import com.d23alex.vtbstat.entities.Team;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TeamRepository extends CrudRepository<Team, Long> {
    List<Team> findAll();
}
