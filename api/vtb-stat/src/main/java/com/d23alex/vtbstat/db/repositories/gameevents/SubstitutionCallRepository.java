package com.d23alex.vtbstat.db.repositories.gameevents;

import com.d23alex.vtbstat.entities.gameevents.SubstitutionCall;
import org.springframework.data.repository.CrudRepository;

public interface SubstitutionCallRepository extends CrudRepository<SubstitutionCall, Long> {
    Iterable<SubstitutionCall> findAllByGameId(long id);
}
