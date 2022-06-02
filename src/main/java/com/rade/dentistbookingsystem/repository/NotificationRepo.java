package com.rade.dentistbookingsystem.repository;

import com.rade.dentistbookingsystem.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepo extends JpaRepository<Notification, Integer> {
}
