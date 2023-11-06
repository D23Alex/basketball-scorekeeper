package com.d23alex.vtbstat.repository;

import com.d23alex.vtbstat.model.Team;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TeamRepository extends CrudRepository<Team, Long> {
    List<Team> findAll();
}
