package com.d23alex.vtbstat.model.gameevents;

import com.d23alex.vtbstat.model.Game;
import com.d23alex.vtbstat.model.Player;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/* Выход игрока c поля на скамейку запасных*/
@Table(name="substitution_outs")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubstitutionOut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    Game game;
    /* Во время какой замены произошёл выход игрока с поля? */
    @ManyToOne
    SubstitutionCall substitutionCall;
    @ManyToOne
    Player player;
}
