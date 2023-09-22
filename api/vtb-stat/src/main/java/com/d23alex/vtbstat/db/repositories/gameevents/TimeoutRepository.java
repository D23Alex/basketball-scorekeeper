package com.d23alex.vtbstat.db.repositories.gameevents;

import com.d23alex.vtbstat.entities.gameevents.Timeout;
import org.springframework.data.repository.CrudRepository;

public interface TimeoutRepository extends CrudRepository<Timeout, Long> {
    Iterable<Timeout> findAllByGameId(long id);
}
