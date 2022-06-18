package com.rade.dentistbookingsystem.componentform;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DoctorAndDate {
    private int branch_id;
    private int doctor_id;
    private String date;
    private String total_time;
}
