package com.rade.dentistbookingsystem.controller;

import com.rade.dentistbookingsystem.componentform.JsonPhone;
import com.rade.dentistbookingsystem.model.AccountDTO;
import com.rade.dentistbookingsystem.model.StoreOTP;
import com.rade.dentistbookingsystem.model.StoreOTPList;
import com.rade.dentistbookingsystem.services.AccountService;
import com.rade.dentistbookingsystem.services.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RestController
@CrossOrigin
@RequestMapping("rade/account")
public class AccountController {
    @Autowired
    AccountService accountService;
    @Autowired
    SmsService service;

    @PostMapping("sendOTP")
    public ResponseEntity<?> smsSubmit(@RequestBody JsonPhone jsonPhone) {
        String otp;
        try {
            otp = service.send(jsonPhone);
            if (otp.length() != 6)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        StoreOTPList.storeOTP(new StoreOTP(jsonPhone.getPhone(), otp, new Date()));
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("verifyOTP")
    public ResponseEntity<?> verifyOTP(@RequestBody StoreOTP receivedOTP) {
        try {
            StoreOTP storeOTP = StoreOTPList.getStoredOTP(receivedOTP.getPhone());
            if (storeOTP == null) ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            return StoreOTPList.verifyOTP(storeOTP, receivedOTP) ? ResponseEntity.status(HttpStatus.OK).build() : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("checkphone")
    public ResponseEntity<?> isRegistrable(@Validated @RequestBody AccountDTO accountDTO) {
        try {
            return accountService.isRegistrable(accountDTO) ? ResponseEntity.status(HttpStatus.OK).build() : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("registration")
    public ResponseEntity<?> register(@Validated @RequestBody AccountDTO accountDTO) {
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
