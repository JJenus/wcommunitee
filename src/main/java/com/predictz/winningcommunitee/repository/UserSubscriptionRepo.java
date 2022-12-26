package com.predictz.winningcommunitee.repository;

import com.predictz.winningcommunitee.model.Subscription;
import com.predictz.winningcommunitee.model.UserSubscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserSubscriptionRepo extends JpaRepository<UserSubscription, Long> {
    List<UserSubscription> findByUserId(Long userId);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "DELETE FROM user_subscription WHERE subscription_id=?1", nativeQuery = true)
    void deleteSubscription(Long id);

    void deleteBySubscription(Subscription subscription);
}
