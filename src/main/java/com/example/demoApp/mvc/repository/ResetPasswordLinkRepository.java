package com.example.demoApp.mvc.repository;

import com.example.demoApp.mvc.entity.ResetPasswordLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResetPasswordLinkRepository  extends JpaRepository<ResetPasswordLink, Integer> {
    public long countByLink(String link);

    public long countByEmail(String email);

    public ResetPasswordLink findByLink(String link);
}
