package com.predictz.predictz.controller.btips;

import com.predictz.predictz.model.btips.Testimonial;
import com.predictz.predictz.service.btips.BtipsTestimonialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/btips/testimonials")
public class BtipsTestimonials {
    @Autowired
    BtipsTestimonialService btipsTestimonialService;

    @GetMapping
    List<Testimonial> get(){
        return btipsTestimonialService.get();
    }

    @PostMapping
    Testimonial create(@RequestBody Testimonial testimonial){
        return  btipsTestimonialService.save(testimonial);
    }

    @PutMapping
    Testimonial update(@RequestBody Testimonial testimonial){
        return btipsTestimonialService.update(testimonial);
    }

    @DeleteMapping("/{id}")
    void del(@PathVariable("id") Long id){
        btipsTestimonialService.del(id);
    }

//    @GetMapping("/won")
//    List<Winner> getWon(){
//        return btipsTestimonialService.getWinners();
//    }
//
//    @PostMapping("/won")
//    Winner createWon(@RequestBody Winner winner){
//        return  btipsTestimonialService.saveWon(winner);
//    }
//
//    @DeleteMapping("/won/{id}")
//    void delWon(@PathVariable("id") Long id){
//        btipsTestimonialService.delWinner(id);
//    }
}
