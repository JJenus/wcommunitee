package com.predictz.winningcommunitee.controller;

import com.predictz.winningcommunitee.model.AppUser;
import com.predictz.winningcommunitee.repository.AppUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private AppUserRepo appUserRepo;

    @GetMapping
    public List<AppUser> appUsers(){
        return appUserRepo.findAll();
    }
}
