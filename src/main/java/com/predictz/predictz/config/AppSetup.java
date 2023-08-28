package com.predictz.predictz.config;

import com.predictz.predictz.model.ROLE;
import com.predictz.predictz.model.btips.Role;
import com.predictz.predictz.model.btips.Setting;
import com.predictz.predictz.repository.btips.BtipsRoleRepo;
import com.predictz.predictz.repository.solidpay.RoleRepo;
import com.predictz.predictz.service.btips.BtipsSettingsService;
import com.predictz.predictz.service.solidpay.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppSetup {
    @Autowired
    private SettingsService settingsService;
    @Autowired
    private RoleRepo roleRepo;
//    Btips
    @Autowired
    private BtipsSettingsService btipsSettingsService;
    @Autowired
    private BtipsRoleRepo btipsRoleRepo;

    private final String LANG = "EN";
    private final String CURRENCY = "USD";
    private final String SYMBOL = "$";

    @Bean
    public void btipsDefaultSettings(){
        try{
            Setting setting = btipsSettingsService.getSettings();
            if (setting == null){
                setting = Setting.builder()
                        .language(LANG)
                        .currency(CURRENCY)
                        .symbol(SYMBOL)
                        .build();
                btipsSettingsService.saveSetting(setting);
            }

            if (!btipsRoleRepo.findByName(ROLE.ADMIN.name()).isPresent())
                btipsRoleRepo.save(new Role(ROLE.ADMIN.name()));

            if (!btipsRoleRepo.findByName(ROLE.PUNTER.name()).isPresent())
                btipsRoleRepo.save(new Role(ROLE.PUNTER.name()));

        }catch (Exception err){
            err.printStackTrace();
        }
    }
    @Bean
    public void solidpayDefaultSettings(){
        try{
            com.predictz.predictz.model.solidpay.Setting setting = settingsService.getSettings();
            if (setting == null){
                setting = com.predictz.predictz.model.solidpay.Setting.builder()
                        .language(LANG)
                        .currency(CURRENCY)
                        .symbol(SYMBOL)
                        .build();
                settingsService.saveSetting(setting);
            }

            if (!roleRepo.findByName(ROLE.ADMIN.name()).isPresent())
                roleRepo.save(new com.predictz.predictz.model.solidpay.Role(ROLE.ADMIN.name()));

            if (!roleRepo.findByName(ROLE.PUNTER.name()).isPresent())
                roleRepo.save(new com.predictz.predictz.model.solidpay.Role(ROLE.PUNTER.name()));

        }catch (Exception err){
            err.printStackTrace();
        }
    }
}
