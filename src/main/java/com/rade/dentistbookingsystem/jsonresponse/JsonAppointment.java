package com.rade.dentistbookingsystem.jsonresponse;

import com.rade.dentistbookingsystem.model.AppointmentDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class JsonAppointment {
    private AppointmentDTO appointmentDTO;
    private int[] serviceIdList;
    private int[] doctorIdList;
}
