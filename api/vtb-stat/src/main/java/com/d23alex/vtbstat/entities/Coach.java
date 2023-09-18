package com.d23alex.vtbstat.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Table(name="coaches")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coach {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    String firstName;
    String lastName;
    Date dateOfBirth;
    String description;
}
