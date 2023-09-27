package com.d23alex.vtbstat.db.repositories.gameevents;

import com.d23alex.vtbstat.entities.gameevents.PeriodStartingLineupOccurrence;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface PeriodStartingLineupOccurrenceRepository extends CrudRepository<PeriodStartingLineupOccurrence, Long> {
    Set<PeriodStartingLineupOccurrence> findAllByGameId(long id);
}
