package com.d23alex.vtbstat.db.repositories.gameevents;

import com.d23alex.vtbstat.entities.gameevents.SubstitutionOut;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface SubstitutionOutRepository extends CrudRepository<SubstitutionOut, Long> {
    Set<SubstitutionOut> findAllByGameId(long id);
}
