package com.d23alex.vtbstat.db.repositories.gameevents;

import com.d23alex.vtbstat.entities.gameevents.CoachEjection;
import org.springframework.data.repository.CrudRepository;

public interface CoachEjectionRepository extends CrudRepository<CoachEjection, Long> {
    Iterable<CoachEjection> findAllByGameId(long id);
}
