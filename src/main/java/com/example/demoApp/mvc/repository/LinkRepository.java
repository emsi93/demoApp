package com.example.demoApp.mvc.repository;

import com.example.demoApp.mvc.entity.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkRepository extends JpaRepository<Link, Integer> {
    public long countByLink(String link);

    public long countByEmailAndType(String email, String type);

    public Link findByLink(String link);

    public Link findByLinkAndType(String link, String type);

    public void deleteByEmailAndType(String email, String type);
}
