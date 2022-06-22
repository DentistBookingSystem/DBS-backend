package com.rade.dentistbookingsystem.controller.admin;

import com.rade.dentistbookingsystem.componentform.StatusForAppointment;
import com.rade.dentistbookingsystem.domain.Appointment;
import com.rade.dentistbookingsystem.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("rade/admin/appointment")
public class AppointmentAdminController {
    @Autowired
    AppointmentService appointmentService;

    @GetMapping("{i}")
    public Page<Appointment> getAppointmentList(@PathVariable Integer i){
        if(i == null) i = 1;
        return appointmentService.findAll(PageRequest.of(i - 1, 20, Sort.by("id").descending()));
    }

    @PostMapping("check")
    public boolean checkAppointment(@RequestBody StatusForAppointment statusForAppointment){
        appointmentService.check(statusForAppointment.getStatus(), statusForAppointment.getId());
        return true;
    }


    @GetMapping("filter")
    public List<Appointment> findAppointmentByMakingDateAndStatus(@RequestParam String date, @RequestParam int status){
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date searchDate = simpleDateFormat.parse(date);
            return null;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;

    }

    @GetMapping("cancel")
    public ResponseEntity<?> cancelAppointment(@RequestParam int id) {

        return ResponseEntity.ok(appointmentService.cancelAppointmentForAdmin(id));


    }


}
