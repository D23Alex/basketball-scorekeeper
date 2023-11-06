package com.d23alex.vtbstat.repository.gameevents;

import com.d23alex.vtbstat.model.gameevents.PlayerTechnicalFoul;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlayerTechnicalFoulRepository extends CrudRepository<PlayerTechnicalFoul, Long> {
    List<PlayerTechnicalFoul> findAllByGameIdOrderByMillisecondsSinceStart(long id);
}
