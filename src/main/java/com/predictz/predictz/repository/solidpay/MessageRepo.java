package com.predictz.predictz.repository.solidpay;

import com.predictz.predictz.model.solidpay.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepo extends JpaRepository<Message, Long> {
}
