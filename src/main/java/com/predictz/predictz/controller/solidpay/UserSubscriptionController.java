package com.predictz.predictz.controller.solidpay;

import com.predictz.predictz.model.solidpay.SubscribeUser;
import com.predictz.predictz.model.solidpay.UserSubscription;
import com.predictz.predictz.service.solidpay.UserSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user-subscriptions")
public class UserSubscriptionController {
    @Autowired
    UserSubscriptionService userSubscriptionService;
    @PostMapping
    public ResponseEntity<?> subscribeUser(@RequestBody SubscribeUser subscribeUser){
        UserSubscription userSubscription = userSubscriptionService.addSubscriptionToUser(
                subscribeUser.getUserId(),
                subscribeUser.getSubscriptionId(),
                subscribeUser.getLength());

        if (userSubscription == null){
            return ResponseEntity.ok(Collections.singletonMap("error", "invalid credentials"));
        }

        return ResponseEntity.ok(userSubscription);
    }

    @GetMapping("/{userId}")
    public List<UserSubscription> userSubscriptions(@PathVariable("userId") Long userId){
        return userSubscriptionService.findUserSubscriptions(userId);
    }

    @GetMapping
    public List<UserSubscription> getAll(){
        return userSubscriptionService.getAll();
    }

    @PutMapping
    public ResponseEntity<?> updateSubscription(@RequestBody UserSubscription subscription){
        UserSubscription userSubscription =  userSubscriptionService.update(subscription);

        if (userSubscription == null){
            return ResponseEntity.ok(Collections.singletonMap("error", "invalid Id"));
        }

        return ResponseEntity.ok(userSubscription);
    }

    @DeleteMapping("/{id}")
    public void deleSub(@PathVariable("id") Long id){
        userSubscriptionService.deleteSub(id);
    }
}
