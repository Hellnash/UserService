package com.lcwd.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "users")
public class User {

    @Id
    private UUID userId;
    @Column(name = "user_name", length = 15)
    private String name;
    @Column(name = "email_id")
    private String email;
    private String about;
}
