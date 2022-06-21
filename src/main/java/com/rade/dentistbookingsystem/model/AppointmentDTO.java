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
    private Integer id;
    private Integer accountId;
    private int branchId;
    private int doctorId;
    private String date;
    private String time;
    private int status;

    public AppointmentDTO(int branchId){
        this.branchId = branchId;
    }
}
