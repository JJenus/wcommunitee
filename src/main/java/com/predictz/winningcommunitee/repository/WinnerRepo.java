package com.predictz.winningcommunitee.repository;

import com.predictz.winningcommunitee.model.Winner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WinnerRepo extends JpaRepository<Winner, Long> {
}
