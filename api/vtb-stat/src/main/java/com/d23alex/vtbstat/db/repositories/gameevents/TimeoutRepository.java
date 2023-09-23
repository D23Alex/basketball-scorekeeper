package com.d23alex.vtbstat.db.repositories.gameevents;

import com.d23alex.vtbstat.entities.gameevents.Timeout;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TimeoutRepository extends CrudRepository<Timeout, Long> {
    List<Timeout> findAllByGameIdOrderByMillisecondsSinceStart(long id);
}
