package com.predictz.predictz.controller.solidpay;

import com.predictz.predictz.model.solidpay.Message;
import com.predictz.predictz.service.solidpay.MessageService;
import com.predictz.predictz.service.solidpay.TelegramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/interact")
public class Interactions {
    @Autowired
    private MessageService messageService;
    @Autowired
    private TelegramService telegramService;

    @PostMapping("/messages")
    public void saveMessage(@RequestBody Message message){
        messageService.saveMessage(message);
    }

    @GetMapping("/messages")
    public List<Message> getMessages(){
        return messageService.getMessages();
    }

    @GetMapping("/messages/count")
    public Map<String, Long> getMessageCount(){
        Map<String, Long> map = new HashMap<>();
        map.put("count", messageService.getCount());
        return map;
    }

    @PostMapping("/telegram/click")
    public void saveTelegramInteraction(){
        telegramService.saveClick();
    }

    @GetMapping("/telegram/count")
    public Map<String, Long> getClickCount(){
        Map<String, Long> count = new HashMap<>();
        count.put("count", telegramService.getCount());
        return count;
    }
}
