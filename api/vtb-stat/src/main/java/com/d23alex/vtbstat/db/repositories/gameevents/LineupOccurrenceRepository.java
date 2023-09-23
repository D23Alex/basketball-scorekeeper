package com.d23alex.vtbstat.db.repositories.gameevents;

import com.d23alex.vtbstat.entities.gameevents.LineupOccurrence;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface LineupOccurrenceRepository extends CrudRepository<LineupOccurrence, Long> {
    Set<LineupOccurrence> findAllByGameId(long id);
}
