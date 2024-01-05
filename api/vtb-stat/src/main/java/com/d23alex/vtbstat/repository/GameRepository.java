package com.d23alex.vtbstat.repository;

import com.d23alex.vtbstat.model.Game;
import com.d23alex.vtbstat.model.Team;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface GameRepository extends CrudRepository<Game, Long> {
    Set<Game> findAllByTeam1IsOrTeam2Is(Team team1, Team team2);
    @Query("SELECT g FROM Game g WHERE g.scheduledStartTime > ?1 ORDER BY g.scheduledStartTime LIMIT ?2")
    List<Game> findNUpcoming(Date date, Long n);
    @Query("SELECT g FROM Game g WHERE g.scheduledStartTime < ?1 ORDER BY g.scheduledStartTime LIMIT ?2")
    List<Game> findNMostRecent(Date date, Long n);
    List<Game> findAllByScheduledStartTimeAfterAndScheduledStartTimeBeforeOrderByScheduledStartTime(Date from, Date to);
}
