package com.d23alex.vtbstat.db.repositories.gameevents;

import com.d23alex.vtbstat.entities.gameevents.PlayerEjection;
import org.springframework.data.repository.CrudRepository;

public interface PlayerEjectionRepository extends CrudRepository<PlayerEjection, Long> {
    Iterable<PlayerEjection> findAllByGameId(long id);
}
