package com.example.demoApp.mvc.entity;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "user", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "login", nullable = false, unique = true, length=20)
    private String login;

    @Column(name = "email", nullable = false, unique = true, length=30)
    private String email;

    @Column(name = "password", nullable = false, length=90)
    private String password;

    @Column(name = "active", nullable = false)
    private boolean active;

    @Column(name = "role", nullable = false, length=20)
    private String role;

    public User(){

    }

    public User(String login, String email, String password, boolean active, String role) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.active = active;
        this.role = role;
    }

    public User(Integer id, String login, String email, String password, boolean active, String role) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.password = password;
        this.active = active;
        this.role = role;
    }
}
