package com.predictz.winningcommunitee.controller;

import com.predictz.winningcommunitee.model.FreePick;
import com.predictz.winningcommunitee.model.SuperPick;
import com.predictz.winningcommunitee.model.VipPick;
import com.predictz.winningcommunitee.service.FreePickService;
import com.predictz.winningcommunitee.service.SuperPicksService;
import com.predictz.winningcommunitee.service.VipPickService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/predictions")
public class Predictions {
    @Autowired
    private VipPickService pickService;
    @Autowired
    private SuperPicksService superPicksService;
    @Autowired
    FreePickService freePickService;

    @PostMapping("/vip-picks")
    public VipPick vipPicks(@RequestBody VipPick pick){
        return pickService.savePick(pick);
    }

    @PostMapping("/vip-picks/{id}")
    public VipPick vipPick(@PathVariable("id") long id, @RequestBody VipPick pick){
        return pickService.updatePick(id, pick);
    }

    @GetMapping("/vip-picks")
    public List<VipPick> vipPicks(){
        return pickService.getPicks();
    }

    @GetMapping("/free-picks")
    public List<FreePick> getFreePicks(){
        return freePickService.getPicks();
    }

    @GetMapping("/super-picks")
    public List<SuperPick> getSuperPicks(){
        return superPicksService.getPicks();
    }
}
