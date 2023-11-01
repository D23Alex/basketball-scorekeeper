package com.d23alex.vtbstat.db.repository.gameevents;

import com.d23alex.vtbstat.entity.gameevents.AfterTimeoutCourtAppearance;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface AfterTimeoutCourtAppearanceRepository extends CrudRepository<AfterTimeoutCourtAppearance, Long> {
    Set<AfterTimeoutCourtAppearance> findAllByGameId(long id);
}
