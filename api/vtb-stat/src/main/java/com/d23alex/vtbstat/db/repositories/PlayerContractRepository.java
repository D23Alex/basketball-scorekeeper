package com.d23alex.vtbstat.db.repositories;

import com.d23alex.vtbstat.entities.Player;
import com.d23alex.vtbstat.entities.PlayerContract;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.Set;

public interface PlayerContractRepository extends CrudRepository<PlayerContract, Long> {
    Set<PlayerContract> findAllByTeamIdAndValidFromBeforeAndValidToAfter(Long teamId, Date validFrom, Date validTo);
}
