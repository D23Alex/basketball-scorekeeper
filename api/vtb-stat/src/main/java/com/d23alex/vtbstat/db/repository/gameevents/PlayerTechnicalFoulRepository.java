package com.d23alex.vtbstat.db.repository.gameevents;

import com.d23alex.vtbstat.entity.gameevents.PlayerTechnicalFoul;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlayerTechnicalFoulRepository extends CrudRepository<PlayerTechnicalFoul, Long> {
    List<PlayerTechnicalFoul> findAllByGameIdOrderByMillisecondsSinceStart(long id);
}
