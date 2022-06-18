package com.rade.dentistbookingsystem.services;

import com.rade.dentistbookingsystem.componentform.DoctorAndDate;
import com.rade.dentistbookingsystem.componentform.JsonAppointment;
import com.rade.dentistbookingsystem.domain.Account;
import com.rade.dentistbookingsystem.domain.Appointment;
import com.rade.dentistbookingsystem.model.AppointmentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface AppointmentService {
//    abstract Appointment save(AppointmentDTO appointmentDTO);

    Appointment save(AppointmentDTO appointmentDTO);

    Page<Appointment> findAll(Pageable pageable);

    Appointment findId(int id);

    void check(Integer status, Integer id);

    List<Appointment> findByAccountId(int account_id, Pageable pageable);


    Appointment findByAccountAndStatus(Account account, int status);

    boolean checkAppointmentToCancel(int id);

    void deleteById(int id);

    List<String> checkTimeOptionOfDoctorByDate(DoctorAndDate doctorAndDate) throws Exception;

    List<String> checkTimeOptionByDate(DoctorAndDate doctorAndDate) throws Exception;

    Appointment checkValidAndSave(JsonAppointment jsonAppointment);
}

