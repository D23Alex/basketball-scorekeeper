package com.d23alex.vtbstat.db.repositories.gameevents;

import com.d23alex.vtbstat.entities.gameevents.PeriodStarter;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface PeriodStarterRepository extends CrudRepository<PeriodStarter, Long> {
    Set<PeriodStarter> findAllByGameId(long id);
}
