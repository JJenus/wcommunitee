package com.predictz.predictz.service.btips;

import com.predictz.predictz.model.btips.Subscription;
import com.predictz.predictz.repository.btips.BtipsSubscriptionRepo;
import com.predictz.predictz.repository.btips.BtipsUserSubscriptionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BtipsSubscriptionService {
    @Autowired
    BtipsSubscriptionRepo subscriptionRepo;
    @Autowired
    BtipsUserSubscriptionRepo userSubscriptionRepo;

    public List<Subscription> getSubscriptions(){
        return subscriptionRepo.findAll();
    }
    public List<Subscription> getSubscriptions(String type){
        return subscriptionRepo.findAll(type);
    }

    public Subscription getSubscription(Long id){
        return subscriptionRepo.findById(id).orElse(null);
    }

    public Subscription save(Subscription Subscription){
        return subscriptionRepo.save(Subscription);
    }

    public Subscription update(Subscription subscription){
        Optional<Subscription> optionalSubscription = subscriptionRepo.findById(subscription.getId());
        if (!optionalSubscription.isPresent())
            return null;
        return subscriptionRepo.save(subscription);
    }

    public void del(Subscription Subscription){
        subscriptionRepo.delete(Subscription);
    }

    public void del(Long id){
        Subscription subscription = getSubscription(id);
//        userSubscriptionRepo.deleteBySubscription(subscription);
        userSubscriptionRepo.deleteSubscription(id);
        subscriptionRepo.deleteById(id);
    }
}
