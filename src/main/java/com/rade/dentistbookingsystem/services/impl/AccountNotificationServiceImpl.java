package com.rade.dentistbookingsystem.services.impl;

import com.rade.dentistbookingsystem.repository.AccountNotificationRepo;
import com.rade.dentistbookingsystem.services.AccountNotificationService;
import org.springframework.stereotype.Service;

@Service
public class AccountNotificationServiceImpl implements AccountNotificationService {
    AccountNotificationRepo accountNotificationRepo;

    public AccountNotificationServiceImpl(AccountNotificationRepo accountNotificationRepo) {
        this.accountNotificationRepo = accountNotificationRepo;
    }

}
