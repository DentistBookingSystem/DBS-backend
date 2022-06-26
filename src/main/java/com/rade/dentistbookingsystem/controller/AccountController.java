package com.rade.dentistbookingsystem.controller;

import com.rade.dentistbookingsystem.domain.Account;
import com.rade.dentistbookingsystem.model.AccountDTO;
import com.rade.dentistbookingsystem.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("rade/account")
public class AccountController {
    @Autowired
    AccountService accountService;


    @PostMapping("registration")
    public ResponseEntity<?> register( @Validated  @RequestBody AccountDTO accountDTO) {
        try {
            final int ROLE_USER = 2;
            accountService.registerNewUserAccount(accountDTO, ROLE_USER);
            return ResponseEntity.ok("Register successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Can not register");
    }

}
