package com.example.demoApp.mvc.entity;




import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "pracownik", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
public class Pracownik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "imie", nullable = false)
    private String imie;

    @Column(name = "nazwisko", nullable = false)
    private String nazwisko;

    public Pracownik() {
    }

    public Pracownik(String imie, String nazwisko) {
        super();
        this.imie = imie;
        this.nazwisko = nazwisko;
    }

    public Pracownik(Integer id, String imie, String nazwisko) {
        super();
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
    }
}