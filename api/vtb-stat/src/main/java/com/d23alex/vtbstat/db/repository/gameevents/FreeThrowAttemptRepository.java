package com.d23alex.vtbstat.db.repository.gameevents;

import com.d23alex.vtbstat.entity.gameevents.FreeThrowAttempt;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FreeThrowAttemptRepository extends CrudRepository<FreeThrowAttempt, Long> {
    List<FreeThrowAttempt> findAllByGameIdOrderByMillisecondsSinceStart(long id);
}
