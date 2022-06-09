package com.rade.dentistbookingsystem.services.impl;
import com.rade.dentistbookingsystem.domain.Account;
import com.rade.dentistbookingsystem.domain.Appointment;
import com.rade.dentistbookingsystem.model.AppointmentDTO;
import com.rade.dentistbookingsystem.repository.AppointmentRepo;
import com.rade.dentistbookingsystem.services.AccountService;
import com.rade.dentistbookingsystem.services.AppointmentService;
import com.rade.dentistbookingsystem.services.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    AppointmentRepo appointmentRepo;
    @Autowired
    private AccountService accountService;
    @Autowired
    private BranchService branchService;
    public AppointmentServiceImpl(AppointmentRepo appointmentRepo) {
        this.appointmentRepo = appointmentRepo;
    }

    @Override
    public Appointment save(AppointmentDTO appointmentDTO){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH");
        Account account;
        if(appointmentDTO.getAccount_id() == null){
            account = null;
        }
        else{
            account = accountService.findId(appointmentDTO.getAccount_id());
        }
        try{
            Appointment appointment = new Appointment(
                    account,
                    branchService.findId(appointmentDTO.getBranch_id()),
                    appointmentDTO.getName(),
                    appointmentDTO.getPhone(),
                    dateFormat.parse(appointmentDTO.getDate()),
                    timeFormat.parse(appointmentDTO.getTime()),
                    0,
                    new Date()
            );
            return appointmentRepo.save(appointment);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Page<Appointment> findAll(Pageable pageable) {
        return appointmentRepo.findAll(pageable);
    }



    @Override
    public void check(Integer status, Integer id) {
        appointmentRepo.check(status, id);
    }
}
