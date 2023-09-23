package com.d23alex.vtbstat.db.repositories.gameevents;

import com.d23alex.vtbstat.entities.gameevents.PlayerEjection;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlayerEjectionRepository extends CrudRepository<PlayerEjection, Long> {
    List<PlayerEjection> findAllByGameIdOrderByMillisecondsSinceStart(long id);
}
