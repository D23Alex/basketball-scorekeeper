package com.d23alex.vtbstat.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Table(name="coach_contracts")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoachContract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    Coach coach;
    @ManyToOne
    Team team;
    Date validFrom;
    Date validTo;
}
