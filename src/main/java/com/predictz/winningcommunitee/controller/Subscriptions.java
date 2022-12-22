package com.predictz.winningcommunitee.controller;

import com.predictz.winningcommunitee.model.Subscription;
import com.predictz.winningcommunitee.service.SubscriptionService;
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
