package com.predictz.predictz.controller.btips;

import com.predictz.predictz.model.btips.FAQ;
import com.predictz.predictz.service.btips.BtipsFAQsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/btips/faqs")
public class BtipsFAQsController {
    @Autowired
    BtipsFAQsService btipsFaQsService;

    @GetMapping
    List<FAQ> getFAQs(){
        return btipsFaQsService.getFAQs();
    }

    @PostMapping
    FAQ create(@RequestBody FAQ faq){
        return btipsFaQsService.saveFaQ(faq);
    }

    @PutMapping
    FAQ update(@RequestBody FAQ faq){
        return btipsFaQsService.update(faq);
    }

    @DeleteMapping("/{id}")
    void del(@PathVariable Long id){
        btipsFaQsService.delFaq(id);
    }
}
