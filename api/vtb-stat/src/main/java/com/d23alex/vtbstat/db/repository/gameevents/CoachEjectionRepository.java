package com.d23alex.vtbstat.db.repository.gameevents;

import com.d23alex.vtbstat.entity.gameevents.CoachEjection;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CoachEjectionRepository extends CrudRepository<CoachEjection, Long> {
    List<CoachEjection> findAllByGameIdOrderByMillisecondsSinceStart(long id);
}
