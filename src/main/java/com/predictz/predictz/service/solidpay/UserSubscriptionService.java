package com.predictz.predictz.service.solidpay;

import com.predictz.predictz.model.solidpay.AppUser;
import com.predictz.predictz.model.solidpay.Subscription;
import com.predictz.predictz.model.solidpay.UserSubscription;
import com.predictz.predictz.repository.solidpay.AppUserRepo;
import com.predictz.predictz.repository.solidpay.SubscriptionRepo;
import com.predictz.predictz.repository.solidpay.UserSubscriptionRepo;
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

    public void deleteSub(Long id) {
        userSubscriptionRepo.deleteById(id);
    }
}
