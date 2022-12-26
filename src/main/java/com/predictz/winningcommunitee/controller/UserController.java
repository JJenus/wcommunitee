package com.predictz.winningcommunitee.controller;

import com.predictz.winningcommunitee.model.AppUser;
import com.predictz.winningcommunitee.repository.AppUserRepo;
import com.predictz.winningcommunitee.service.AppUserService;
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
}
