package com.rade.dentistbookingsystem.repository;

import com.rade.dentistbookingsystem.domain.AccountNotification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountNotificationRepo extends JpaRepository<AccountNotification, Integer> {
}
