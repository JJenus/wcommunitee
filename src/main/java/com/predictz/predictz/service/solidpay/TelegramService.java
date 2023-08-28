package com.predictz.predictz.service.solidpay;

import com.predictz.predictz.model.solidpay.TelegramInteraction;
import com.predictz.predictz.repository.solidpay.TelegramRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TelegramService {
    @Autowired
    private TelegramRepo telegramRepo;

    public void saveClick(){
        telegramRepo.save(new TelegramInteraction());
    }

    public long getCount(){
        return telegramRepo.count();
    }
}
