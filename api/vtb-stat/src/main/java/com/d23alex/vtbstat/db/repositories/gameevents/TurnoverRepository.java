package com.d23alex.vtbstat.db.repositories.gameevents;

import com.d23alex.vtbstat.entities.gameevents.Turnover;
import org.springframework.data.repository.CrudRepository;

public interface TurnoverRepository extends CrudRepository<Turnover, Long> {
    Iterable<Turnover> findAllByGameId(long id);
}
