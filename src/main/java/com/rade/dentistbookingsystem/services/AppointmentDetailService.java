package com.rade.dentistbookingsystem.services;

import com.rade.dentistbookingsystem.domain.AppointmentDetail;

import java.util.List;

public interface AppointmentDetailService {
    AppointmentDetail save(AppointmentDetail appointmentDetail);

    List<AppointmentDetail> findByAppointmentId(int appointment_id);
}
