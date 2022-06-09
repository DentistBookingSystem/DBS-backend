package com.rade.dentistbookingsystem.services;

import com.rade.dentistbookingsystem.domain.Account;
import com.rade.dentistbookingsystem.model.AccountDTO;

public interface AccountService {
    Account findId(int id);

    <S extends Account> S save(S entity);

    Account registerNewUserAccount(AccountDTO accountDTO) throws Exception;

    Account view(String phone);

    Account edit(AccountDTO accountDTO) throws Exception;

    Account findByPhone(String phone);
}
