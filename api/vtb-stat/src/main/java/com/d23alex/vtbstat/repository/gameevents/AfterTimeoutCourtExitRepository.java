package com.d23alex.vtbstat.repository.gameevents;

import com.d23alex.vtbstat.model.gameevents.AfterTimeoutCourtExit;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface AfterTimeoutCourtExitRepository extends CrudRepository<AfterTimeoutCourtExit, Long> {
    Set<AfterTimeoutCourtExit> findAllByGameId(long id);
}
