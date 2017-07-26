package com.example.demoApp.mvc.entity;


import javax.persistence.*;

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

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Pracownik [id=" + id + ", imie=" + imie + ", nazwisko=" + nazwisko + "]";
    }

}