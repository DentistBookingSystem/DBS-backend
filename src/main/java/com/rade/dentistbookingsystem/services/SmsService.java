package com.rade.dentistbookingsystem.services;

import com.rade.dentistbookingsystem.componentform.JsonPhone;
import com.rade.dentistbookingsystem.model.StoreOTPList;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Random;

@Component
@Service
public class SmsService {

    private final String ACCOUNT_SID ="AC0dcea50829a1f572a348d76ece647dff";

    private final String AUTH_TOKEN = "5056115e74e9573baf1994bfc9314561";

    private final String FROM_NUMBER = "+14323005761";

    private final int OTP_LENGTH = 6;

    public String send(JsonPhone jsonPhone) throws ParseException {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        String otp = "";
        otp = "";
        Random generator = new Random();
        for(int i=0; i<OTP_LENGTH; i++){
            int a = generator.nextInt()%10;
            if(a<0){
                a = -a;
            }
            otp = otp.concat(Integer.toString(a));
        }
        String msg = "Mã OTP xác thực sđt cho tài khoản Nha Khoa RaDe của bạn là: " + otp + ". Mã có hiệu lực trong vòng " + StoreOTPList.VALID_TIME_FOR_VERIFICATION_AS_SECOND/60 + " phút.";
        MessageCreator messageCreator = Message.creator(new PhoneNumber(jsonPhone.getPhone()), new PhoneNumber(FROM_NUMBER), msg);
        messageCreator.create();
        return otp;
    }
}

