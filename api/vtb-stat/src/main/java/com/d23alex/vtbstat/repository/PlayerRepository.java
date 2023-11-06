package com.d23alex.vtbstat.repository;

import com.d23alex.vtbstat.model.Player;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PlayerRepository extends CrudRepository<Player, Long> {

    Optional<Player> findPlayerByFirstNameAndLastName(String firstName, String lastName);
}
