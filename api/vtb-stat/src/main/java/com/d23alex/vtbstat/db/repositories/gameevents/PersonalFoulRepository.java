package com.d23alex.vtbstat.db.repositories.gameevents;

import com.d23alex.vtbstat.entities.gameevents.PersonalFoul;
import org.springframework.data.repository.CrudRepository;

public interface PersonalFoulRepository extends CrudRepository<PersonalFoul, Long> {
    Iterable<PersonalFoul> findAllByGameId(long id);
}
