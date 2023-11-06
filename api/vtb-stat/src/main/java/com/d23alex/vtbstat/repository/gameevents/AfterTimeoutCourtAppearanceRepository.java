package com.d23alex.vtbstat.repository.gameevents;

import com.d23alex.vtbstat.model.gameevents.AfterTimeoutCourtAppearance;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface AfterTimeoutCourtAppearanceRepository extends CrudRepository<AfterTimeoutCourtAppearance, Long> {
    Set<AfterTimeoutCourtAppearance> findAllByGameId(long id);
}
