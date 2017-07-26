package com.example.demoApp.mvc.repository;

import com.example.demoApp.mvc.entity.Pracownik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PracownikRepository extends JpaRepository<Pracownik, Integer> {

    List<Pracownik> findByImie(String imie);

}
