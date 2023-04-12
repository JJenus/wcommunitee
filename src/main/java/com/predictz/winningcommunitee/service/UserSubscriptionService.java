package com.predictz.winningcommunitee.service;

import com.predictz.winningcommunitee.model.AppUser;
import com.predictz.winningcommunitee.model.Subscription;
import com.predictz.winningcommunitee.model.UserSubscription;
import com.predictz.winningcommunitee.repository.AppUserRepo;
import com.predictz.winningcommunitee.repository.SubscriptionRepo;
import com.predictz.winningcommunitee.repository.UserSubscriptionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserSubscriptionService {
    @Autowired
    UserSubscriptionRepo userSubscriptionRepo;
    @Autowired
    SubscriptionRepo subscriptionRepo;
    @Autowired
    AppUserRepo userRepo;

    public UserSubscription addSubscriptionToUser(Long userId, Long subscriptionId, int length){
        Subscription subscription = subscriptionRepo.findById(subscriptionId).orElse(null);
        AppUser user = userRepo.findById(userId).orElse(null);

        if (subscription == null || user == null) return null;

        UserSubscription userSubscription = new UserSubscription();
        userSubscription.setSubscription(subscription);
        userSubscription.setLength(length);
        userSubscription.setUserId(userId);

        return userSubscriptionRepo.save(userSubscription);
    }

    public UserSubscription update(UserSubscription subscription){
        if (subscription.getId() == null)
            return null;
        return userSubscriptionRepo.save(subscription);
    }

    public List<UserSubscription> getAll(){
        return userSubscriptionRepo.findAll();
    }

    public List<UserSubscription> findUserSubscriptions(Long userId){
        return userSubscriptionRepo.findByUserId(userId);
    }
}
