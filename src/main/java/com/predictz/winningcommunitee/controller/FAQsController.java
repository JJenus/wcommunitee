package com.predictz.winningcommunitee.controller;

import com.predictz.winningcommunitee.model.FAQ;
import com.predictz.winningcommunitee.service.FAQsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/faqs")
public class FAQsController {
    @Autowired
    FAQsService faQsService;

    @GetMapping
    List<FAQ> getFAQs(){
        return faQsService.getFAQs();
    }

    @PostMapping
    FAQ create(@RequestBody FAQ faq){
        return faQsService.saveFaQ(faq);
    }

    @PutMapping
    FAQ update(@RequestBody FAQ faq){
        return faQsService.update(faq);
    }

    @DeleteMapping("/{id}")
    void del(Long id){
        faQsService.delFaq(id);
    }
}
