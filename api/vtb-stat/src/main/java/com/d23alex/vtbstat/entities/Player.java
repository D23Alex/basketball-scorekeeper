package com.d23alex.vtbstat.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Table(name="players")
@Entity

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {

    public enum Position {
        POINT_GUARD,
        SHOOTING_GUARD,
        SMALL_FORWARD,
        POWER_FORWARD,
        CENTER
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;

    String firstName;
    String lastName;
    Date   dateOfBirth;

    String   profileImagePath;
    String   description;
    Position position;
}
