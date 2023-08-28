package com.predictz.predictz.repository.btips;

import com.predictz.predictz.model.btips.FAQ;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BtipsFAQsRepo extends JpaRepository<FAQ, Long> {
}
