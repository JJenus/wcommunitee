package com.predictz.winningcommunitee.repository;

import com.predictz.winningcommunitee.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepo extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByEmail(String email);
}
