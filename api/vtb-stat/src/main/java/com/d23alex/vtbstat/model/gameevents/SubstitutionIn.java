package com.d23alex.vtbstat.model.gameevents;

import com.d23alex.vtbstat.model.Game;
import com.d23alex.vtbstat.model.Player;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/* Выход игрока на поле со скамейки запасных*/
@Table(name="substitution_ins")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubstitutionIn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    Game game;
    /* Во время какой замены произошёл выход игрока на площадку? */
    @ManyToOne
    SubstitutionCall substitutionCall;
    @ManyToOne
    Player player;
}
