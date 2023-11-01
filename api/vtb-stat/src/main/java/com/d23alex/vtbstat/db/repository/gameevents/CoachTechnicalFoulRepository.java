package com.d23alex.vtbstat.db.repository.gameevents;

import com.d23alex.vtbstat.entity.gameevents.CoachTechnicalFoul;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CoachTechnicalFoulRepository extends CrudRepository<CoachTechnicalFoul, Long> {
    List<CoachTechnicalFoul> findAllByGameIdOrderByMillisecondsSinceStart(long id);
}
