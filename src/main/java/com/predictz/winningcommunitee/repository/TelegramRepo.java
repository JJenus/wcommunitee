package com.predictz.winningcommunitee.repository;

import com.predictz.winningcommunitee.model.TelegramInteraction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelegramRepo extends JpaRepository<TelegramInteraction, Long> {
}
