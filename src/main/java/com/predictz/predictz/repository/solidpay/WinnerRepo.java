package com.predictz.predictz.repository.solidpay;

import com.predictz.predictz.model.solidpay.Winner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WinnerRepo extends JpaRepository<Winner, Long> {
}
