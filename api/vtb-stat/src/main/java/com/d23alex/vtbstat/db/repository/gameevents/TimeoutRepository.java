package com.d23alex.vtbstat.db.repository.gameevents;

import com.d23alex.vtbstat.entity.gameevents.Timeout;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TimeoutRepository extends CrudRepository<Timeout, Long> {
    List<Timeout> findAllByGameIdOrderByMillisecondsSinceStart(long id);
}
