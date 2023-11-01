package com.d23alex.vtbstat.db.repository.gameevents;

import com.d23alex.vtbstat.entity.gameevents.PersonalFoul;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonalFoulRepository extends CrudRepository<PersonalFoul, Long> {
    List<PersonalFoul> findAllByGameIdOrderByMillisecondsSinceStart(long id);
}
