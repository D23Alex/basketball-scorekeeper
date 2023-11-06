package com.d23alex.vtbstat.repository;

import com.d23alex.vtbstat.model.Game;
import com.d23alex.vtbstat.model.Team;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface GameRepository extends CrudRepository<Game, Long> {
    Set<Game> findAllByTeam1IsOrTeam2Is(Team team1, Team team2);
}
