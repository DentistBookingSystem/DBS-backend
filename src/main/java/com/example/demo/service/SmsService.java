package com.example.demo.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.demo.dto.SmsPojo;
import com.twilio.rest.api.v2010.account.MessageCreator;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
@Component
@Service
public class SmsService {

    private final String ACCOUNT_SID ="AC9e05131b45ffa2056b97d5666160b894";

    private final String AUTH_TOKEN = "d025c3fed2fa808126317400a7062346";

    private final String FROM_NUMBER = "+13392177822";

    public void send(SmsPojo sms) throws ParseException {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);


        int min = 100000;
        int max = 999999;
        int number=(int)(Math.random()*(max-min+1)+min);


        String msg = String.valueOf(number);

        MessageCreator messageCreator = Message.creator(new PhoneNumber(sms.getPhoneNo()), new PhoneNumber(FROM_NUMBER), msg);
        System.out.println();
        messageCreator.create();

      /*  Date myDate=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = sdf. parse(myDate.toString());
        long millis = date. getTime();
        System.out.println(millis);
       OTPpojo.setOtp(number);
        System.out.println("here is my id:"+message.getSid());// Unique resource ID created to manage this transaction
*/
    }

    public void receive(MultiValueMap<String, String> smscallback) {

    }

}

