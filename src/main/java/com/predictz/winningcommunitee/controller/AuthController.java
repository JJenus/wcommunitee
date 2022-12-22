package com.predictz.winningcommunitee.controller;

import com.predictz.winningcommunitee.model.AppUser;
import com.predictz.winningcommunitee.model.AuthToken;
import com.predictz.winningcommunitee.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AppUser user){
        System.out.println("This wa hit");
        AuthToken res = authService.login(user);
        if (res == null){
            return ResponseEntity.ok(Collections.singletonMap("error", "User doesn't exist"));
        }

        return ResponseEntity.ok(res);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody AppUser user){
        AuthToken res = authService.registerUser(user);

        if (res == null){
            return ResponseEntity.ok(Collections.singletonMap("error", "Email already exists"));
        }
        return ResponseEntity.ok(res);
    }
}
