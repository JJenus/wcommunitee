package com.predictz.predictz.service.btips;

import com.predictz.predictz.model.btips.Setting;
import com.predictz.predictz.repository.btips.BtipsSettingsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BtipsSettingsService {
    @Autowired
    private BtipsSettingsRepo settingsRepo;

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
