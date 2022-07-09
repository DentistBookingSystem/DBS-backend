package com.rade.dentistbookingsystem.controller.staff;

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
@RequestMapping("rade/staff/account")
public class AccountStaffController {


    @Autowired
    AccountService accountService;

    @PostMapping("profile/edit")
    public ResponseEntity<?> editStaff(@Validated @RequestBody AccountDTO accountDTO) throws Exception {
        Account account = accountService.edit(accountDTO);
        if (account != null)
            return ResponseEntity.ok("Edit successfully");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Can not Edit");
    }

    @GetMapping("profile")
    public Account viewProfile(@RequestParam String phone) {

        return accountService.view(phone);

    }


}
