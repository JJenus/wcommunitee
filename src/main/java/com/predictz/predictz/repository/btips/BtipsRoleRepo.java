package com.predictz.predictz.repository.btips;

import com.predictz.predictz.model.btips.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BtipsRoleRepo extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
