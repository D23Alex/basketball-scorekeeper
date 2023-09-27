package com.d23alex.vtbstat.controllers;

import com.d23alex.vtbstat.db.DatabaseQueries;
import com.d23alex.vtbstat.db.repositories.PlayerRepository;
import com.d23alex.vtbstat.entities.Player;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.Optional;

@RestController
public class PlayerController {
    //TODO перейти от DatabaseQueries к отдельному репозиторию в каждом контроллере
    private final DatabaseQueries databaseQueries;

    private final PlayerRepository playerRepository;

    public PlayerController(DatabaseQueries databaseQueries, PlayerRepository playerRepository) {
        this.databaseQueries = databaseQueries;
        this.playerRepository = playerRepository;
    }

    //TODO изменить путь запроса (/api мне не нравится, не лучше ли оставить /players/{id}?)
    @GetMapping("/api/players/{id}")
    Optional<Player> getPlayerById(@PathVariable Long id) {
        return databaseQueries.playerById(id);
    }

    @GetMapping("/api/players/byteam/{teamId}/{date}")
    Iterable<Player> playersOfTeamByDate(@PathVariable Long teamId, @PathVariable Date date) {
        return databaseQueries.teamMembersByDate(teamId, date);
    }

    /* TODO 1) доделать метод поиска игрока по имени и фамилии в репозитории
     *       2) сделать метод добавления игрока в PlayerController
     *       3) протестировать систему хранения изображения профиля игрока
     *
     */
    ResponseEntity<String> addPlayer(@RequestBody Player player) {
        //FIXME результат запроса к бд неоднозначен, лучше делать выборку по всем параметрам сразу
        Optional<Player> optPlayer = playerRepository.findPlayerByFirstNameAndLastName(
                player.getFirstName(), player.getLastName()
        );

        if (optPlayer.isPresent()) {
            Player foundPlayer = optPlayer.get();

            boolean isMatch = foundPlayer.equals(player);
            if (!isMatch) {
                playerRepository.save(player);
                return new ResponseEntity<>("Player has been successfully added!", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Player already exists!", HttpStatus.BAD_REQUEST);
            }

        } else {
            playerRepository.save(player);
            return new ResponseEntity<>("Player has been successfully added!", HttpStatus.OK);
        }
    }

}
