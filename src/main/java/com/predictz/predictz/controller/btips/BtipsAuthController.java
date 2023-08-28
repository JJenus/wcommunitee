package com.predictz.predictz.controller.btips;

import com.predictz.predictz.model.AuthToken;
import com.predictz.predictz.model.PasswordReset;
import com.predictz.predictz.model.btips.AppUser;
import com.predictz.predictz.service.btips.BtipsAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@CrossOrigin("*")
@RestController
@RequestMapping("/btips/auth")
public class BtipsAuthController {
    @Autowired
    BtipsAuthService btipsAuthService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AppUser user){
        AuthToken res = btipsAuthService.login(user);
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
        AuthToken res = btipsAuthService.registerUser(user);

        if (res == null){
            return ResponseEntity.ok(Collections.singletonMap("error", "Email already exists"));
        }
        return ResponseEntity.ok(res);
    }

    @PostMapping("/reset-password")
    public void requestPassReset(@RequestBody PasswordReset passwordReset) throws IllegalAccessException {
        btipsAuthService.sendPasswordReset(passwordReset);
    }

    @PostMapping("/change-password")
    public String resetPassword(@RequestBody PasswordReset passwordReset) {
        String message = "success";
        try {
            btipsAuthService.resetPassword(passwordReset);
        } catch (IllegalAccessException e) {
            message = e.getMessage();
        }

        return message;
    }
}
