package com.d23alex.vtbstat.db.repositories.gameevents;

import com.d23alex.vtbstat.entities.gameevents.PlayerTechnicalFoul;
import org.springframework.data.repository.CrudRepository;

public interface PlayerTechnicalFoulRepository extends CrudRepository<PlayerTechnicalFoul, Long> {
    Iterable<PlayerTechnicalFoul> findAllByGameId(long id);
}
