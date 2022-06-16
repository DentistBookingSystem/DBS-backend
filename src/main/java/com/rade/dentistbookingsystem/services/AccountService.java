package com.rade.dentistbookingsystem.services;

import com.rade.dentistbookingsystem.componentform.AccountAndViolationTimes;
import com.rade.dentistbookingsystem.domain.Account;
import com.rade.dentistbookingsystem.model.AccountDTO;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public interface AccountService {
    Account findId(int id);

    <S extends Account> S save(S entity);

    Account registerNewUserAccount(AccountDTO accountDTO) throws Exception;

    Account view(String phone);

    Account edit(AccountDTO accountDTO) throws Exception;

//    List<AccountAndViolationTimes> findAccountViolated(Pageable pageable);

    List<AccountAndViolationTimes> findViolatedAccountsAndViolationTimes(Pageable pageable);

    Account findByPhone(String phone);
}
