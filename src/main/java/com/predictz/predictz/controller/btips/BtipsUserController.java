package com.predictz.predictz.controller.btips;

import com.predictz.predictz.model.UUser;
import com.predictz.predictz.model.btips.AppUser;
import com.predictz.predictz.service.btips.BtipsAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/btips/users")
public class BtipsUserController {
    @Autowired
    private BtipsAppUserService btipsAppUserService;

    @GetMapping
    public List<UUser> appUsers(){
        return btipsAppUserService.getUsers();
    }

    @PutMapping
    public UUser update(@RequestBody AppUser appUser){
        return btipsAppUserService.update(appUser);
    }

    @GetMapping("/{id}")
    public UUser getUser(@PathVariable("id") Long id){
        return btipsAppUserService.getUser(id).getUUser();
    }
}
