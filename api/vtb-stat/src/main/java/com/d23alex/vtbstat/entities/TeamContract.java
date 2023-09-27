package com.d23alex.vtbstat.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Table(name="team_contracts")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamContract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    Team team;
    Date validFrom;
    Date validTo;
}
