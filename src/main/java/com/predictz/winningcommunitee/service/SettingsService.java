package com.predictz.winningcommunitee.service;

import com.predictz.winningcommunitee.model.Setting;
import com.predictz.winningcommunitee.repository.SettingsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SettingsService {
    @Autowired
    private SettingsRepo settingsRepo;

    public Setting saveSetting(Setting setting){
        return settingsRepo.save(setting);
    }

    public Setting getSettings() {
        List<Setting> settings = settingsRepo.findAll();
        if (settings.isEmpty())
            return new Setting();
        return settings.get(0);
    }
}
