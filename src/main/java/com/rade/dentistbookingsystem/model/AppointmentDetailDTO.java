package com.rade.dentistbookingsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppointmentDetailDTO implements Serializable {
    private int id;
    private int apponitment_id;
    private int service_id;
    private int doctor_id;
    private int discount_id;

}
