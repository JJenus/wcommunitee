package com.predictz.predictz.repository.solidpay;

import com.predictz.predictz.model.solidpay.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubscriptionRepo extends JpaRepository<Subscription, Long> {
    @Query(value = "SELECT * FROM subscription WHERE type=?1", nativeQuery = true)
    List<Subscription> findAll(String type);
}
