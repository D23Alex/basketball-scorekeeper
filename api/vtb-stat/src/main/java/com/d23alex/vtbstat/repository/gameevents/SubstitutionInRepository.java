package com.d23alex.vtbstat.repository.gameevents;

import com.d23alex.vtbstat.model.gameevents.SubstitutionIn;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface SubstitutionInRepository extends CrudRepository<SubstitutionIn, Long> {
    Set<SubstitutionIn> findAllByGameId(long id);
}
