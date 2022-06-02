package com.rade.dentistbookingsystem.services.impl;

import com.rade.dentistbookingsystem.domain.Account;
import com.rade.dentistbookingsystem.repository.AccountRepo;
import com.rade.dentistbookingsystem.services.AccountService;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    AccountRepo accountRepo;

    public AccountServiceImpl(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    public Account findId(int id) {
        return accountRepo.findId(id);
    }
}
