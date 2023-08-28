package com.predictz.predictz.controller.solidpay;

import com.predictz.predictz.model.ROLE;
import com.predictz.predictz.model.solidpay.Setting;
import com.predictz.predictz.service.solidpay.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("settings")
public class Settings {
    @Autowired
    private SettingsService settingsService;

    @PostMapping
    public Setting setting(@RequestBody Setting setting){
        return settingsService.saveSetting(setting);
    }

    @GetMapping
    public Setting getSettings(){
        System.out.println(ROLE.ADMIN);
        return settingsService.getSettings();
    }

    @PostMapping("/password")
    public void changePassword(){}

}
