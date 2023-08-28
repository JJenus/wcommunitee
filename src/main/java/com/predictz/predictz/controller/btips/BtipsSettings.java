package com.predictz.predictz.controller.btips;

import com.predictz.predictz.model.ROLE;
import com.predictz.predictz.model.btips.Setting;
import com.predictz.predictz.service.btips.BtipsSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/btips/settings")
public class BtipsSettings {
    @Autowired
    private BtipsSettingsService btipsSettingsService;

    @PostMapping
    public Setting setting(@RequestBody Setting setting){
        return btipsSettingsService.saveSetting(setting);
    }

    @GetMapping
    public Setting getSettings(){
        System.out.println(ROLE.ADMIN);
        return btipsSettingsService.getSettings();
    }

    @PostMapping("/password")
    public void changePassword(){}

}
