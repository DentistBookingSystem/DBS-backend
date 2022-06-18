package com.rade.dentistbookingsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppointmentDTO  implements Serializable {
    private int id;
    private Integer account_id;
    private int branch_id;
    private int doctor_id;
    private String date;
    private String time;
    private int status;

    public AppointmentDTO(int branch_id){
        this.branch_id = branch_id;
    }
}
