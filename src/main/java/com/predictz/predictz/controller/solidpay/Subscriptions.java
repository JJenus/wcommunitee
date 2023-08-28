package com.predictz.predictz.controller.solidpay;

import com.predictz.predictz.model.solidpay.Subscription;
import com.predictz.predictz.service.solidpay.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/subscriptions")
public class Subscriptions {
    @Autowired
    SubscriptionService subscriptionService;

    @GetMapping
    List<Subscription> getSubscriptions(@RequestParam(value = "type", defaultValue = "all") String type){
        if (!type.equalsIgnoreCase("all")){
            return subscriptionService.getSubscriptions(type);
        }
        return subscriptionService.getSubscriptions();
    }

    @PostMapping
    Subscription create(@RequestBody Subscription subscription){
        return subscriptionService.save(subscription);
    }

    @PutMapping
    Subscription update(@RequestBody Subscription subscription){
        return subscriptionService.update(subscription);
    }

    @DeleteMapping("{id}")
    void del(@PathVariable("id") Long id){
        subscriptionService.del(id);
    }
}
