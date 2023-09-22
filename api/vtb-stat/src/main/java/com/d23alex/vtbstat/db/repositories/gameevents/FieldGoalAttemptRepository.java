package com.d23alex.vtbstat.db.repositories.gameevents;

import com.d23alex.vtbstat.entities.gameevents.FieldGoalAttempt;
import org.springframework.data.repository.CrudRepository;

public interface FieldGoalAttemptRepository extends CrudRepository<FieldGoalAttempt, Long> {
    Iterable<FieldGoalAttempt> findAllByGameId(long id);
}
