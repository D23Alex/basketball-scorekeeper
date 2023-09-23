package com.d23alex.vtbstat.db.repositories.gameevents;

import com.d23alex.vtbstat.entities.gameevents.StartingLineupOccurrence;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface StartingLineupOccurrenceRepository extends CrudRepository<StartingLineupOccurrence, Long> {
    Set<StartingLineupOccurrence> findAllByGameId(long id);
}
