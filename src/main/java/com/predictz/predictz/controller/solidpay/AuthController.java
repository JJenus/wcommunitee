package com.predictz.predictz.controller.solidpay;

import com.predictz.predictz.model.solidpay.AppUser;
import com.predictz.predictz.model.AuthToken;
import com.predictz.predictz.service.solidpay.AuthService;
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
        AuthToken res = authService.login(user);
        if (res == null){
            return ResponseEntity.ok(Collections.singletonMap("error", "User doesn't exist"));
        }
        if (res.getToken() == null){
            return ResponseEntity.ok(Collections.singletonMap("error", "Invalid password"));
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
