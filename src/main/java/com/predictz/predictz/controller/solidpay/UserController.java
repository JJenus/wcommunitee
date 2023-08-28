package com.predictz.predictz.controller.solidpay;

import com.predictz.predictz.model.solidpay.AppUser;
import com.predictz.predictz.service.solidpay.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private AppUserService appUserService;

    @GetMapping
    public List<AppUser> appUsers(){
        return appUserService.getUsers();
    }

    @PutMapping
    public AppUser update(@RequestBody AppUser appUser){
        return appUserService.update(appUser);
    }

    @GetMapping("/{id}")
    public AppUser getUser(@PathVariable("id") Long id){
        return appUserService.getUser(id);
    }
}
