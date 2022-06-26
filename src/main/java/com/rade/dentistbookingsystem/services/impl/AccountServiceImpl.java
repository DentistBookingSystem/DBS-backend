package com.rade.dentistbookingsystem.services.impl;

import com.rade.dentistbookingsystem.componentform.AccountAndViolationTimes;
import com.rade.dentistbookingsystem.domain.Account;
import com.rade.dentistbookingsystem.model.AccountDTO;
import com.rade.dentistbookingsystem.repository.*;
import com.rade.dentistbookingsystem.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    AccountRepo accountRepo;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    RoleRepo roleRepo;

    @Autowired
    AppointmentRepo appointmentRepo;

    @Autowired
    DistrictRepo districtRepo;

    public AccountServiceImpl(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    public Account findId(int id) {
        return accountRepo.findId(id);
    }

    @Override
    public <S extends Account> S save(S entity) {
        return accountRepo.save(entity);
    }


    @Override
    public Account registerNewUserAccount(AccountDTO accountDTO) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        int roleUserId = 2;
        if (accountRepo.findByPhone(accountDTO.getPhone()) != null) {
            throw new Exception(
                    accountDTO.getPhone() + "is already exist");
        }
        Account account = new Account();
        account.setFullName(accountDTO.getFullName());
        account.setPhone(accountDTO.getPhone());
        account.setDateOfBirth(dateFormat.parse(accountDTO.getDateOfBirth()));
        account.setRole(roleRepo.getById(roleUserId));
        account.setStatus((short) 1);
        account.setEmail(accountDTO.getEmail());
        account.setPassword(bCryptPasswordEncoder.encode(accountDTO.getPassword()));
        account.setDistrict(districtRepo.getById(accountDTO.getDistrictId()));
        account.setGender(accountDTO.getGender());
        return save(account);
    }

    @Override
    public Account view(String phone) {
        Account account = accountRepo.findByPhone(phone);
        account.setPassword("*******");
        return account;

    }

    @Override
    public Account edit(AccountDTO accountDTO) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Account account = accountRepo.findByPhone(accountDTO.getPhone());
        if (account == null) {
            throw new Exception("Can not edit!! please try again");
        } else {
            account.setFullName(accountDTO.getFullName());
            account.setDateOfBirth(dateFormat.parse(accountDTO.getDateOfBirth()));
            account.setEmail(accountDTO.getEmail());
            account.setPassword(bCryptPasswordEncoder.encode(accountDTO.getPassword()));
            account.setDistrict(districtRepo.getById(accountDTO.getDistrictId()));
            account.setGender(accountDTO.getGender());
            return save(account);
        }
    }


    @Override
    public List<AccountAndViolationTimes> findViolatedAccountsAndViolationTimes(Pageable pageable) {
        List<Account> accountList = accountRepo.findAccountViolated(pageable);
        List<AccountAndViolationTimes> accountAndViolationTimesList = new ArrayList<>();
        for (Account account : accountList) {
            int violationTimes =
                    appointmentRepo.countByAccountIdAndStatus(account.getId(), 2);                ;
            accountAndViolationTimesList.add(new AccountAndViolationTimes(
                    account,
                    violationTimes
            ));
        }
        return accountAndViolationTimesList;
    }

    public Account findByPhone(String phone) {
        return accountRepo.findByPhone(phone);
    }

    @Override
    public void checkAccount(Integer status, Integer id) {
        accountRepo.checkAccount(status, id);
    }

    @Override
    public List<Account> getAccountList(int roleId, short status){
        return accountRepo.filterAccount(roleId, status);
    }
}