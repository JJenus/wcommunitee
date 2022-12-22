package com.predictz.winningcommunitee.repository;

import com.predictz.winningcommunitee.model.ApiLimit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiLimitRepo extends JpaRepository<ApiLimit, Long> {
    ApiLimit findByApiName(String name);
}
