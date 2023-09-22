package com.d23alex.vtbstat.db.repositories;

import com.d23alex.vtbstat.entities.Game;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, Long> {
}
