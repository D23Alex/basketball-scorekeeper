package com.d23alex.vtbstat.db.repositories.gameevents;

import com.d23alex.vtbstat.entities.gameevents.Turnover;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TurnoverRepository extends CrudRepository<Turnover, Long> {
    List<Turnover> findAllByGameIdOrderByMillisecondsSinceStart(long id);
}
