package com.example.demoapp.mvc.repository;

import com.example.demoapp.mvc.entity.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkRepository extends JpaRepository<Link, Integer> {
    long countByLink(String link);

    long countByEmailAndType(String email, String type);

    Link findByLink(String link);

    Link findByLinkAndType(String link, String type);

    Link findByEmailAndType(String email, String type);

    void deleteByEmailAndType(String email, String type);
}
