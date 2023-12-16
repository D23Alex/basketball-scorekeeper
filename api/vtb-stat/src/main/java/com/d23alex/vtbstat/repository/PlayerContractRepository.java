package com.d23alex.vtbstat.repository;

import com.d23alex.vtbstat.model.PlayerContract;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface PlayerContractRepository extends CrudRepository<PlayerContract, Long> {
    Set<PlayerContract> findAllByTeamIdAndValidFromBeforeAndValidToAfter(Long teamId, Date validFrom, Date validTo);
    List<PlayerContract> findAllByPlayerIdAndValidFromBeforeAndValidToAfterOrderByValidFrom(Long playerId, Date validFrom, Date validTo);
    Set<PlayerContract> findAllByPlayerIdAndValidFromBefore(Long playerId, Date validFrom);
    Set<PlayerContract> findAllByPlayerId(Long playerId);
}
