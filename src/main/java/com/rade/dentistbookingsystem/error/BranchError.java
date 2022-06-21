package com.rade.dentistbookingsystem.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BranchError {
    private String nameError;

    private String districtIdError;

    private String urlError;

    private String openTimeError;

    private String closeTimeError;

    private String statusError;
}
