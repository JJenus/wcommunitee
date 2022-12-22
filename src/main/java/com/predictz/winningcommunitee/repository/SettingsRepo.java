package com.predictz.winningcommunitee.repository;

import com.predictz.winningcommunitee.model.Setting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SettingsRepo extends JpaRepository<Setting, Long> {
}
