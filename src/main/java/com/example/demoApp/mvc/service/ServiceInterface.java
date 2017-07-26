package com.example.demoApp.mvc.service;

import com.example.demoApp.mvc.entity.Pracownik;

import java.util.List;
import java.util.Optional;

public interface ServiceInterface {

    List<Pracownik> findByImie(String imie);

    Pracownik findById(Integer id);

    void save(Pracownik pracownik);

    List<Pracownik> findAll();

    void update(Pracownik pracownik);

    void delete(Integer id);

    void delete(Pracownik pracownik);
}
