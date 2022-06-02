package com.rade.dentistbookingsystem.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AppointmentError {
    private String nameError;
    private String phoneError;
    private String dateError;
    private String timeError;
    private String serviceListError;
}
