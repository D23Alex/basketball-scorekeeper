package com.d23alex.vtbstat.repository.gameevents;

import com.d23alex.vtbstat.model.gameevents.SubstitutionOut;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface SubstitutionOutRepository extends CrudRepository<SubstitutionOut, Long> {
    Set<SubstitutionOut> findAllByGameId(long id);
}
