package com.predictz.winningcommunitee.repository;

import com.predictz.winningcommunitee.model.FAQ;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FAQsRepo extends JpaRepository<FAQ, Long> {
}
