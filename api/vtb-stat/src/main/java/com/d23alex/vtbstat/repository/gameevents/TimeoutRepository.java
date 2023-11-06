package com.d23alex.vtbstat.repository.gameevents;

import com.d23alex.vtbstat.model.gameevents.Timeout;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TimeoutRepository extends CrudRepository<Timeout, Long> {
    List<Timeout> findAllByGameIdOrderByMillisecondsSinceStart(long id);
}
