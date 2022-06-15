package com.rade.dentistbookingsystem.services.impl;
import com.rade.dentistbookingsystem.domain.Account;
import com.rade.dentistbookingsystem.domain.Appointment;
import com.rade.dentistbookingsystem.model.AppointmentDTO;
import com.rade.dentistbookingsystem.repository.AppointmentRepo;
import com.rade.dentistbookingsystem.services.AccountService;
import com.rade.dentistbookingsystem.services.AppointmentService;
import com.rade.dentistbookingsystem.services.BranchService;
import com.rade.dentistbookingsystem.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    AppointmentRepo appointmentRepo;
    @Autowired
    private AccountService accountService;
    @Autowired
    private BranchService branchService;
    @Autowired
    private DoctorService doctorService;
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
                    doctorService.findId(appointmentDTO.getDoctor_id()),
                    dateFormat.parse(appointmentDTO.getDate()),
                    appointmentDTO.getShift(),
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
    public Appointment findId(int id) {
        return appointmentRepo.findId(id);
    }

    @Override
    public List<Appointment> checkShiftOfDoctor(int doctor_id, String time) {
        return appointmentRepo.checkShiftOfDoctor(doctor_id, time);
    }


    @Override
    public List<Appointment> checkShiftOfDoctorOneDay(int doctor_id, String time) {
        return appointmentRepo.checkShiftOfDoctorOneDay(doctor_id, time);
    }

    @Override
    public void check(Integer status, Integer id) {
        appointmentRepo.check(status, id);
    }
    @Override
    public List<Appointment> findByAccountId(int account_id, Pageable pageable) {
        return appointmentRepo.findByAccountId(account_id, pageable);
    }
    @Override
    public Appointment findByShiftAndDateAndDoctorId(int appointment_shift, Date appointment_date, int doctor_id) {
        return appointmentRepo.findByShiftAndDateAndDoctorId(appointment_shift, appointment_date, doctor_id);
    }

    @Override
    public Appointment findByAccountAndStatus(Account account, int status) {
        return appointmentRepo.findByAccountAndStatus(account, status);
    }
}
