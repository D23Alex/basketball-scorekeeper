package com.d23alex.vtbstat.db.repositories.gameevents;

import com.d23alex.vtbstat.entities.gameevents.Rebound;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReboundRepository extends CrudRepository<Rebound, Long> {
    List<Rebound> findAllByGameIdOrderByMillisecondsSinceStart(long id);
}
