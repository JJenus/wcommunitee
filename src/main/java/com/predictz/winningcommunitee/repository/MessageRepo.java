package com.predictz.winningcommunitee.repository;

import com.predictz.winningcommunitee.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepo extends JpaRepository<Message, Long> {
}
