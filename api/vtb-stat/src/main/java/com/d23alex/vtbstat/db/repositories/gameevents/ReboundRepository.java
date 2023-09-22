package com.d23alex.vtbstat.db.repositories.gameevents;

import com.d23alex.vtbstat.entities.gameevents.Rebound;
import org.springframework.data.repository.CrudRepository;

public interface ReboundRepository extends CrudRepository<Rebound, Long> {
    Iterable<Rebound> findAllByGameId(long id);
}
