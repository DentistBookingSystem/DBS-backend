package com.rade.dentistbookingsystem.controller.admin;

import com.rade.dentistbookingsystem.domain.Account;
import com.rade.dentistbookingsystem.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("rade/admin/account")
public class AccountAdminController {

    @Autowired
    AccountService accountService;

    @GetMapping("accountDetail")
    public Account findByPhone(@RequestParam String phone) {
        return accountService.findByPhone(phone);
    }
}

