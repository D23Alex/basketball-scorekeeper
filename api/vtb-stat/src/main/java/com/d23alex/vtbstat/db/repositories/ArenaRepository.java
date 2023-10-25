package com.d23alex.vtbstat.db.repositories;


import com.d23alex.vtbstat.entities.Arena;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface ArenaRepository extends CrudRepository<Arena, Long> {
}
