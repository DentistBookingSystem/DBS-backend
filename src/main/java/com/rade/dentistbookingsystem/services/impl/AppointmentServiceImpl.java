package com.rade.dentistbookingsystem.services.impl;
import com.rade.dentistbookingsystem.componentform.DoctorAndDate;
import com.rade.dentistbookingsystem.componentform.TimeOptionByDate;
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
import java.util.ArrayList;
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

//    @Override
//    public Appointment save(AppointmentDTO appointmentDTO){
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        SimpleDateFormat timeFormat = new SimpleDateFormat("HH");
//        Account account;
//        if(appointmentDTO.getAccount_id() == null){
//            account = null;
//        }
//        else{
//            account = accountService.findId(appointmentDTO.getAccount_id());
//        }
//        try{
//            Appointment appointment = new Appointment(
//                    account,
//                    branchService.findId(appointmentDTO.getBranch_id()),
//                    doctorService.findId(appointmentDTO.getDoctor_id()),
//                    dateFormat.parse(appointmentDTO.getDate()),
//                    dateFormat.parse(appointmentDTO.getTime()),
//                    0,
//                    new Date()
//            );
//            return appointmentRepo.save(appointment);
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//        return null;
//    }

    @Override
    public Page<Appointment> findAll(Pageable pageable) {
        return appointmentRepo.findAll(pageable);
    }

    @Override
    public Appointment findId(int id) {
        return appointmentRepo.findId(id);
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
    public Appointment findByAccountAndStatus(Account account, int status) {
        return appointmentRepo.findByAccountAndStatus(account, status);
    }

    @Override
    public boolean checkAppointmentToCancel(int id) {
        return appointmentRepo.checkAppointmentToCancel(id);
    }

    @Override
    public void deleteById(int id) {
        appointmentRepo.deleteById(id);
    }

//    public List<TimeOptionByDate> checkTimeOptionOfDoctorByDate(DoctorAndDate doctorAndDate){
//        List<TimeOptionByDate> timeOptionByDateList = new ArrayList<>();
//        List<Appointment> appointmentList;
//        if(doctorAndDate.getDoctor_id() != 0){
//            appointmentList = appointmentRepo.findByDoctorIdAndTime(doctorAndDate.getDoctor_id(), doctorAndDate.getDate());
//
//        }
//    }
}
