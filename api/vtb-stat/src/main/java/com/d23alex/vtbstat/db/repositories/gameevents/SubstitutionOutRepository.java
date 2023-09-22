package com.d23alex.vtbstat.db.repositories.gameevents;

import com.d23alex.vtbstat.entities.gameevents.SubstitutionOut;
import org.springframework.data.repository.CrudRepository;

public interface SubstitutionOutRepository extends CrudRepository<SubstitutionOut, Long> {
    Iterable<SubstitutionOut> findAllByGameId(long id);
}
