package com.d23alex.vtbstat.db.repository;

import com.d23alex.vtbstat.entity.Game;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, Long> {
}
