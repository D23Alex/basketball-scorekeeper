package com.d23alex.vtbstat.db.repositories.gameevents;

import com.d23alex.vtbstat.entities.gameevents.SubstitutionIn;
import org.springframework.data.repository.CrudRepository;

public interface SubstitutionInRepository extends CrudRepository<SubstitutionIn, Long> {
    Iterable<SubstitutionIn> findAllByGameId(long id);
}
