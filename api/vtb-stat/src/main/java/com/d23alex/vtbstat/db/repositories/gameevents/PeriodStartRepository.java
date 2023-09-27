package com.d23alex.vtbstat.db.repositories.gameevents;

import com.d23alex.vtbstat.entities.gameevents.PeriodStart;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PeriodStartRepository extends CrudRepository<PeriodStart, Long> {
    List<PeriodStart> findAllByGameId(long gameId);
}
