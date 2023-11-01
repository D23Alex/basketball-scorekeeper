package com.d23alex.vtbstat.db.repository.gameevents;

import com.d23alex.vtbstat.entity.gameevents.PeriodEnder;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface PeriodEnderRepository extends CrudRepository<PeriodEnder, Long> {
    Set<PeriodEnder> findAllByGameId(long id);
}
