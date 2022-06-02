package com.rade.dentistbookingsystem.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BranchError {
    private String nameError;

    private String district_idError;

    private String urlError;

    private String open_timeError;

    private String close_timeError;

    private String statusError;
}
