package com.predictz.predictz.repository.btips;

import com.predictz.predictz.model.btips.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BtipsAppUserRepo extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByEmail(String email);
}
