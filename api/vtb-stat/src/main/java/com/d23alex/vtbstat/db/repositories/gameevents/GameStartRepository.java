package com.d23alex.vtbstat.db.repositories.gameevents;

import com.d23alex.vtbstat.entities.gameevents.GameStart;
import org.springframework.data.repository.CrudRepository;

public interface GameStartRepository extends CrudRepository<GameStart, Long> {
    GameStart findByGameId(long gameId);
}
