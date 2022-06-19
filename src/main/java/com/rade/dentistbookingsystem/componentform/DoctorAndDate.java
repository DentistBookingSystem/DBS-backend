package com.rade.dentistbookingsystem.componentform;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DoctorAndDate {
    private Integer appointment_id;
    private int branch_id;
    private int doctor_id;
    private String date;
    private int [] service_id;
}
