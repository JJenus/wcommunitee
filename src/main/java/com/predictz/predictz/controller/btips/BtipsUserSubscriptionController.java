package com.predictz.predictz.controller.btips;

import com.predictz.predictz.model.btips.SubscribeUser;
import com.predictz.predictz.model.btips.UserSubscription;
import com.predictz.predictz.service.btips.BtipsUserSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/btips/user-subscriptions")
public class BtipsUserSubscriptionController {
    @Autowired
    BtipsUserSubscriptionService btipsUserSubscriptionService;
    @PostMapping
    public ResponseEntity<?> subscribeUser(@RequestBody SubscribeUser subscribeUser){
        UserSubscription userSubscription = btipsUserSubscriptionService.addSubscriptionToUser(
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
        return btipsUserSubscriptionService.findUserSubscriptions(userId);
    }

    @GetMapping
    public List<UserSubscription> getAll(){
        return btipsUserSubscriptionService.getAll();
    }

    @PutMapping
    public ResponseEntity<?> updateSubscription(@RequestBody UserSubscription subscription){
        UserSubscription userSubscription =  btipsUserSubscriptionService.update(subscription);

        if (userSubscription == null){
            return ResponseEntity.ok(Collections.singletonMap("error", "invalid Id"));
        }

        return ResponseEntity.ok(userSubscription);
    }

    @DeleteMapping("/{id}")
    public void deleSub(@PathVariable("id") Long id){
        btipsUserSubscriptionService.deleteSub(id);
    }
}
