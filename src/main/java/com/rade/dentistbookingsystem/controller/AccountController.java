package com.rade.dentistbookingsystem.controller;

import com.rade.dentistbookingsystem.domain.Account;
import com.rade.dentistbookingsystem.model.AccountDTO;
import com.rade.dentistbookingsystem.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("rade/account")
public class AccountController {
    @Autowired
    AccountService accountService;


    @PostMapping("registration")
    public ResponseEntity<?> register(@RequestBody AccountDTO accountDTO) {
        try {
            accountService.registerNewUserAccount(accountDTO);
            return ResponseEntity.ok("Register successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Can not register");
    }

    @GetMapping("profile")
    public Account viewProfile(@RequestParam String phone) {

        return accountService.view(phone);
    }

    @PostMapping("profile/edit")
    public ResponseEntity<?> edit(@RequestBody AccountDTO accountDTO) {
        try {
            return ResponseEntity.ok(accountService.edit(accountDTO));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }


}
