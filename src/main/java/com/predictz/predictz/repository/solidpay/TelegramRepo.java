package com.predictz.predictz.repository.solidpay;

import com.predictz.predictz.model.solidpay.TelegramInteraction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelegramRepo extends JpaRepository<TelegramInteraction, Long> {
}
