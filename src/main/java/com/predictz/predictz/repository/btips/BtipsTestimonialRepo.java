package com.predictz.predictz.repository.btips;

import com.predictz.predictz.model.btips.Testimonial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BtipsTestimonialRepo extends JpaRepository<Testimonial, Long> {
}
