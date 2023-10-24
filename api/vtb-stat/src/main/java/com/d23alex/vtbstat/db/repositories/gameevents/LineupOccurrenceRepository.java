package com.d23alex.vtbstat.db.repositories.gameevents;

import com.d23alex.vtbstat.entities.Player;
import com.d23alex.vtbstat.entities.gameevents.LineupOccurrence;
import org.springframework.data.repository.CrudRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

public interface LineupOccurrenceRepository extends CrudRepository<LineupOccurrence, Long> {
    Set<LineupOccurrence> findAllByGameId(long id);
    Set<LineupOccurrence> findAllByTeamIdAndGameId(long teamId, long gameId);
    List<LineupOccurrence> findAllByPlayerIsAndGameScheduledStartTimeBetween(Player player, Timestamp from, Timestamp to);
}
