package com.d23alex.vtbstat.db.repositories.gameevents;

import com.d23alex.vtbstat.entities.gameevents.LineupOccurrence;
import org.springframework.data.repository.CrudRepository;

public interface LineupOccurrenceRepository extends CrudRepository<LineupOccurrence, Long> {
    Iterable<LineupOccurrence> findAllByGameId(long id);
}
