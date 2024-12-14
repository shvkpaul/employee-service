package com.shvkpaul.employee.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;
    private String surname;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;
}
