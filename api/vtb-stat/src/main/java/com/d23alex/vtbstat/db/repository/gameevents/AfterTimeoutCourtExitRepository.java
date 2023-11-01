package com.d23alex.vtbstat.db.repository.gameevents;

import com.d23alex.vtbstat.entity.gameevents.AfterTimeoutCourtExit;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface AfterTimeoutCourtExitRepository extends CrudRepository<AfterTimeoutCourtExit, Long> {
    Set<AfterTimeoutCourtExit> findAllByGameId(long id);
}
