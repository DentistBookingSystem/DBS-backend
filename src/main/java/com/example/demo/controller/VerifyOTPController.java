package com.example.demo.controller;

import com.example.demo.dto.TempOTP;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VerifyOTPController {

    @PostMapping("/otp")
    public String verifyOTP(@RequestBody TempOTP sms){
        if(sms.getOtp() == 123455)
            return "Correct OTP";
        else
            return "Not correct OTP";
    }
}
