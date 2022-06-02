package com.rade.dentistbookingsystem.repository;

import com.rade.dentistbookingsystem.domain.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedBackRepo extends JpaRepository<Feedback, Integer> {
}
