package com.rade.dentistbookingsystem.services.impl;

import com.rade.dentistbookingsystem.domain.Account;
import com.rade.dentistbookingsystem.model.AccountDTO;
import com.rade.dentistbookingsystem.repository.AccountRepo;
import com.rade.dentistbookingsystem.repository.DistrictRepo;
import com.rade.dentistbookingsystem.repository.RoleRepo;
import com.rade.dentistbookingsystem.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

@Service
public class AccountServiceImpl implements AccountService {
    AccountRepo accountRepo;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    RoleRepo roleRepo;

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
        int role_user_id = 2;
        if (accountRepo.findByPhone(accountDTO.getPhone()) != null) {
            throw new Exception(
                    accountDTO.getPhone() + "is already exist");
        }
        Account account = new Account();
        account.setFull_name(accountDTO.getFull_name());
        account.setPhone(accountDTO.getPhone());
        account.setDate_of_birth(dateFormat.parse(accountDTO.getDate_of_birth()));
        account.setRole(roleRepo.getById(role_user_id));
        account.setStatus((short) 1);
        account.setEmail(accountDTO.getEmail());
        account.setPassword(bCryptPasswordEncoder.encode(accountDTO.getPassword()));
        account.setDistrict(districtRepo.getById(accountDTO.getDistrict_id()));
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
            account.setFull_name(accountDTO.getFull_name());
            account.setDate_of_birth(dateFormat.parse(accountDTO.getDate_of_birth()));
            account.setEmail(accountDTO.getEmail());
            account.setPassword(bCryptPasswordEncoder.encode(accountDTO.getPassword()));
            account.setDistrict(districtRepo.getById(accountDTO.getDistrict_id()));
            account.setGender(accountDTO.getGender());
            return save(account);
        }
    }
}
