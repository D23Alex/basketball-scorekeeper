package com.d23alex.vtbstat.db.repositories.gameevents;

import com.d23alex.vtbstat.entities.gameevents.SubstitutionCall;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SubstitutionCallRepository extends CrudRepository<SubstitutionCall, Long> {
    List<SubstitutionCall> findAllByGameIdOrderByMillisecondsSinceStart(long id);
}
