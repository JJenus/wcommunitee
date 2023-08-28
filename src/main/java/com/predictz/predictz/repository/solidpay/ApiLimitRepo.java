package com.predictz.predictz.repository.solidpay;

import com.predictz.predictz.model.solidpay.ApiLimit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiLimitRepo extends JpaRepository<ApiLimit, Long> {
    ApiLimit findByApiName(String name);
}
