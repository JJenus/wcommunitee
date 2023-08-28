package com.predictz.predictz.repository.btips;

import com.predictz.predictz.model.btips.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BtipsTokenRepo extends JpaRepository<Token, Long> {
    Token findByToken(String token);
}
