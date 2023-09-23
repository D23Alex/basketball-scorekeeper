package com.d23alex.vtbstat.db.repositories.gameevents;

import com.d23alex.vtbstat.entities.gameevents.CoachTechnicalFoul;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CoachTechnicalFoulRepository extends CrudRepository<CoachTechnicalFoul, Long> {
    List<CoachTechnicalFoul> findAllByGameIdOrderByMillisecondsSinceStart(long id);
}
