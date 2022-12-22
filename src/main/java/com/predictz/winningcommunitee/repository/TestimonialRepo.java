package com.predictz.winningcommunitee.repository;

import com.predictz.winningcommunitee.model.Testimonial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestimonialRepo extends JpaRepository<Testimonial, Long> {
}
