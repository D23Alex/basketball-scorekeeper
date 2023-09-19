package com.d23alex.vtbstat.entities.gameevents;

import com.d23alex.vtbstat.entities.Player;
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
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    /* Во время какой замены произошёл выход игрока на площадку? */
    @ManyToOne SubstitutionCall substitutionCall;
    @ManyToOne Player player;
}
