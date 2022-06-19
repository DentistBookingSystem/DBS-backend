package com.rade.dentistbookingsystem.services;

import com.rade.dentistbookingsystem.componentform.AccountAndViolationTimes;
import com.rade.dentistbookingsystem.domain.Account;
import com.rade.dentistbookingsystem.model.AccountDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    Account findId(int id);

    <S extends Account> S save(S entity);


    Account registerNewUserAccount(AccountDTO accountDTO) throws Exception;

    Account view(String phone);

    Account edit(AccountDTO accountDTO) throws Exception;

//    List<AccountAndViolationTimes> findAccountViolated(Pageable pageable);

    List<AccountAndViolationTimes> findViolatedAccountsAndViolationTimes(Pageable pageable);

    Account findByPhone(String phone);

    // public Account getById(Integer id);

    Optional<Account> findById(Integer integer);
}
