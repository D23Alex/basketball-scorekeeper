package com.d23alex.vtbstat.db.repositories.gameevents;

import com.d23alex.vtbstat.entities.gameevents.GameEnding;
import org.springframework.data.repository.CrudRepository;

public interface GameEndingRepository extends CrudRepository<GameEnding, Long> {
    GameEnding findByGameId(long gameId);
}
