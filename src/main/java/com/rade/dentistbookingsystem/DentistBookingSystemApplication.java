package com.rade.dentistbookingsystem;

import com.rade.dentistbookingsystem.domain.Appointment;
import com.rade.dentistbookingsystem.services.AccountService;
import com.rade.dentistbookingsystem.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@SpringBootApplication
public class DentistBookingSystemApplication{
    @Autowired
    AppointmentService appointmentService;
    @Autowired
    AccountService accountService;
    public static void main(String[] args) {
        SpringApplication.run(DentistBookingSystemApplication.class, args);
    }

    @Scheduled(fixedDelayString = "PT1M")
    void checkAllAppointmentToMarkAbsent() throws InterruptedException{
        List<Appointment> appointmentList = appointmentService.findAllAppointmentToMarkAbsent();
        for (Appointment appointment : appointmentList) {
            Integer accountId = appointment.getAccount().getId();
            appointmentService.check(2, appointment.getId());
            if (appointmentService.checkAccountToBanByAppointment(accountId)) {
                accountService.checkAccount(2, accountId);
            }
        }
    }
}
@Configuration
@EnableScheduling
@ConditionalOnProperty(name = "scheduling.enabled", matchIfMissing = true)
class SchedulingOnConfiguration{

}
