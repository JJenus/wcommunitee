package com.predictz.predictz.repository.btips;

import com.predictz.predictz.model.btips.Setting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BtipsSettingsRepo extends JpaRepository<Setting, Long> {
}
