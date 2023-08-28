package com.predictz.predictz.repository.solidpay;

import com.predictz.predictz.model.solidpay.FAQ;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FAQsRepo extends JpaRepository<FAQ, Long> {
}
