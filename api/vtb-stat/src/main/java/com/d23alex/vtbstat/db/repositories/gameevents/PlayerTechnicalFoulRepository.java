package com.d23alex.vtbstat.db.repositories.gameevents;

import com.d23alex.vtbstat.entities.gameevents.PlayerTechnicalFoul;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlayerTechnicalFoulRepository extends CrudRepository<PlayerTechnicalFoul, Long> {
    List<PlayerTechnicalFoul> findAllByGameIdOrderByMillisecondsSinceStart(long id);
}
