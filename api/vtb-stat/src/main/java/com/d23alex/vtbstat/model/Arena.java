package com.d23alex.vtbstat.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="arenas")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Arena {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String address;
}
