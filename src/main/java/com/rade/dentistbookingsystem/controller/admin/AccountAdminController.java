package com.rade.dentistbookingsystem.controller.admin;

import com.rade.dentistbookingsystem.componentform.AccountAndViolationTimes;
import com.rade.dentistbookingsystem.domain.Account;
import com.rade.dentistbookingsystem.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("rade/admin/account")
public class AccountAdminController {
    @Autowired
    AccountService accountService;

    @GetMapping("violated/page/{page}")
    public List<AccountAndViolationTimes> getViolatedAccounts(@PathVariable int page){
        Pageable pageable = PageRequest.of(page - 1, 3);
        return accountService.findViolatedAccountsAndViolationTimes(pageable);
    }

    @GetMapping("accountDetail/{phone}")
    public Account findByPhone(@PathVariable String phone) {
        return accountService.view(phone);
    }


    @GetMapping("list/{roleId}/{status}")
    public List<Account> getAccountList(@PathVariable(name = "roleId") int roleId, @PathVariable(name = "status") short status){
           return  accountService.getAccountList(roleId, status);
    }


}
