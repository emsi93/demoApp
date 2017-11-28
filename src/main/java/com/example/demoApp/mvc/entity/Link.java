package com.example.demoapp.mvc.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "link", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "link", nullable = false, unique = true, length = 100)
    private String link;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "data", nullable = false)
    private LocalDateTime data;

    @Column(name="type", nullable = false)
    private String type;

    public Link() {

    }

    public Link(String link, String email, LocalDateTime data, String type) {
        this.link = link;
        this.email = email;
        this.data = data;
        this.type = type;
    }

    public Link(Integer id, String link, String email, LocalDateTime data, String type) {
        this.id = id;
        this.link = link;
        this.email = email;
        this.data = data;
        this.type = type;
    }
}
