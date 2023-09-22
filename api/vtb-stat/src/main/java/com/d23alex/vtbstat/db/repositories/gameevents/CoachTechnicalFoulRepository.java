package com.d23alex.vtbstat.db.repositories.gameevents;

import com.d23alex.vtbstat.entities.gameevents.CoachTechnicalFoul;
import org.springframework.data.repository.CrudRepository;

public interface CoachTechnicalFoulRepository extends CrudRepository<CoachTechnicalFoul, Long> {
    Iterable<CoachTechnicalFoul> findAllByGameId(long id);
}
