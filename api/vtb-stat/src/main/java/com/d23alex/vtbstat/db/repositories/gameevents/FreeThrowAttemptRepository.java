package com.d23alex.vtbstat.db.repositories.gameevents;

import com.d23alex.vtbstat.entities.gameevents.FreeThrowAttempt;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FreeThrowAttemptRepository extends CrudRepository<FreeThrowAttempt, Long> {
    List<FreeThrowAttempt> findAllByGameIdOrderByMillisecondsSinceStart(long id);
}
