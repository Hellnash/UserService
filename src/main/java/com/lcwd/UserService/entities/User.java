package com.lcwd.UserService.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private String userId;
    @Column(name = "user_name", length = 15)
    private String name;
    @Column(name = "email_id")
    private String email;
    private String about;
    @Transient
    private List<Rating> ratings;
}
