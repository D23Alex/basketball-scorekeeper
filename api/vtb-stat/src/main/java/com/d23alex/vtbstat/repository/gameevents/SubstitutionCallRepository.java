package com.d23alex.vtbstat.repository.gameevents;

import com.d23alex.vtbstat.model.gameevents.SubstitutionCall;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SubstitutionCallRepository extends CrudRepository<SubstitutionCall, Long> {
    List<SubstitutionCall> findAllByGameIdOrderByMillisecondsSinceStart(long id);
}
