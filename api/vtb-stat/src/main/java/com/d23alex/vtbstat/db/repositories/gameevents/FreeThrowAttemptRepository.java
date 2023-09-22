package com.d23alex.vtbstat.db.repositories.gameevents;

import com.d23alex.vtbstat.entities.gameevents.FreeThrowAttempt;
import org.springframework.data.repository.CrudRepository;

public interface FreeThrowAttemptRepository extends CrudRepository<FreeThrowAttempt, Long> {
    Iterable<FreeThrowAttempt> findAllByGameId(long id);
}
