package com.d23alex.vtbstat.repository.gameevents;

import com.d23alex.vtbstat.model.gameevents.FieldGoalAttempt;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FieldGoalAttemptRepository extends CrudRepository<FieldGoalAttempt, Long> {
    List<FieldGoalAttempt> findAllByGameIdOrderByMillisecondsSinceStart(long id);
}
