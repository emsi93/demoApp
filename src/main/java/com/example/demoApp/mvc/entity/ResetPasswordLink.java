package com.example.demoApp.mvc.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "reset_password_link", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class ResetPasswordLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "link", nullable = false, unique = true, length = 100)
    private String link;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    public ResetPasswordLink() {

    }

    public ResetPasswordLink(String link, String email) {
        this.link = link;
        this.email = email;
    }

    public ResetPasswordLink(Integer id, String link, String email) {
        this.id = id;
        this.link = link;
        this.email = email;
    }
}
