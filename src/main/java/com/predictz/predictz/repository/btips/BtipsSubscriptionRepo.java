package com.predictz.predictz.repository.btips;

import com.predictz.predictz.model.btips.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BtipsSubscriptionRepo extends JpaRepository<Subscription, Long> {
    @Query(value = "SELECT * FROM subscription WHERE type=?1", nativeQuery = true)
    List<Subscription> findAll(String type);
}
