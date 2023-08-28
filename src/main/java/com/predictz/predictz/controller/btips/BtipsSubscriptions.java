package com.predictz.predictz.controller.btips;

import com.predictz.predictz.model.btips.Subscription;
import com.predictz.predictz.service.btips.BtipsSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/btips/subscriptions")
public class BtipsSubscriptions {
    @Autowired
    BtipsSubscriptionService btipsSubscriptionService;

    @GetMapping
    List<Subscription> getSubscriptions(@RequestParam(value = "type", defaultValue = "all") String type){
        if (!type.equalsIgnoreCase("all")){
            return btipsSubscriptionService.getSubscriptions(type);
        }
        return btipsSubscriptionService.getSubscriptions();
    }

    @PostMapping
    Subscription create(@RequestBody Subscription subscription){
        return btipsSubscriptionService.save(subscription);
    }

    @PutMapping
    Subscription update(@RequestBody Subscription subscription){
        return btipsSubscriptionService.update(subscription);
    }

    @DeleteMapping("{id}")
    void del(@PathVariable("id") Long id){
        btipsSubscriptionService.del(id);
    }
}
