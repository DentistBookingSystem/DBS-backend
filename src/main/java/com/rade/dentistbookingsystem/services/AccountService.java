package com.rade.dentistbookingsystem.services;

import com.rade.dentistbookingsystem.componentform.AccountAndViolationTimes;
import com.rade.dentistbookingsystem.domain.Account;
import com.rade.dentistbookingsystem.model.AccountDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AccountService {
    Account findId(int id);

    <S extends Account> S save(S entity);

    boolean isRegistrable(AccountDTO accountDTO);

    Account registerNewUserAccount(AccountDTO accountDTO, int role) throws Exception;

    Account view(String phone);

    Account edit(AccountDTO accountDTO) throws Exception;

    boolean confirmPassword(String phone, String password);

    List<AccountAndViolationTimes> findViolatedAccountsAndViolationTimes(Pageable pageable);

    Account findByPhone(String phone);

    void checkAccount(Integer status, Integer id);


    List<Account> getAccountList(int statusId, short status, String phone);

    boolean editStatus(String phone, int status) throws Exception;
}