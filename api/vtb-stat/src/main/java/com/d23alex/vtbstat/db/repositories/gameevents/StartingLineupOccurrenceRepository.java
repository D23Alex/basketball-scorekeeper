package com.d23alex.vtbstat.db.repositories.gameevents;

import com.d23alex.vtbstat.entities.gameevents.StartingLineupOccurrence;
import org.springframework.data.repository.CrudRepository;

public interface StartingLineupOccurrenceRepository extends CrudRepository<StartingLineupOccurrence, Long> {
    Iterable<StartingLineupOccurrence> findAllByGameId(long id);
}
