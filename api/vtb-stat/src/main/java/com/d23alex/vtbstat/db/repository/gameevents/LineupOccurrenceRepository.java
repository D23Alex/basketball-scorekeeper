package com.d23alex.vtbstat.db.repository.gameevents;

import com.d23alex.vtbstat.entity.gameevents.LineupOccurrence;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface LineupOccurrenceRepository extends CrudRepository<LineupOccurrence, Long> {
    Set<LineupOccurrence> findAllByGameId(long id);
}
