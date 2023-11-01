package com.d23alex.vtbstat.db.repository.gameevents;

import com.d23alex.vtbstat.entity.gameevents.PeriodEnding;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PeriodEndingRepository extends CrudRepository<PeriodEnding, Long> {
    List<PeriodEnding> findAllByGameId(long gameId);
}
