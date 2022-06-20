package com.rade.dentistbookingsystem;

import com.rade.dentistbookingsystem.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
public class DentistBookingSystemApplication{
    @Autowired
    AppointmentService appointmentService;
    public static void main(String[] args) {
        SpringApplication.run(DentistBookingSystemApplication.class, args);
    }

//    @Scheduled(fixedDelayString = "PT1M")
//    void checkAllAppointmentToMarkAbsent() throws InterruptedException{
//        appointmentService.checkAllAppointmentToMarkAbsent();
//    }
}
@Configuration
@EnableScheduling
@ConditionalOnProperty(name = "scheduling.enabled", matchIfMissing = true)
class SchedulingOnConfiguration{

}
