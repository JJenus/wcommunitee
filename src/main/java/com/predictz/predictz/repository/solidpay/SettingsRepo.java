package com.predictz.predictz.repository.solidpay;

import com.predictz.predictz.model.solidpay.Setting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SettingsRepo extends JpaRepository<Setting, Long> {
}
