package com.d23alex.vtbstat.db.repository;


import com.d23alex.vtbstat.entity.Arena;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ArenaRepository extends CrudRepository<Arena, Long> {
}
