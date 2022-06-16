package com.rade.dentistbookingsystem.services;

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
    abstract Appointment save(AppointmentDTO appointmentDTO);

    Page<Appointment> findAll(Pageable pageable);

    Appointment findId(int id);

    List<Appointment> checkShiftOfDoctor(int doctor_id, String time);

    List<Appointment> checkShiftOfDoctorOneDay(int doctor_id, String time);

    void check(Integer status, Integer id);

    List<Appointment> findByAccountId(int account_id, Pageable pageable);

    Appointment findByShiftAndDateAndDoctorId(int appointment_shift, Date appointment_date, int doctor_id);

    Appointment findByAccountAndStatus(Account account, int status);

    int countByAccountIdAndStatus(int account_id, int status);
}

