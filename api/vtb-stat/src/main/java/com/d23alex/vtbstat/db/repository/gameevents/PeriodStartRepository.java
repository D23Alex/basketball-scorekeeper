package com.d23alex.vtbstat.db.repository.gameevents;

import com.d23alex.vtbstat.entity.gameevents.PeriodStart;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PeriodStartRepository extends CrudRepository<PeriodStart, Long> {
    List<PeriodStart> findAllByGameId(long gameId);
}
