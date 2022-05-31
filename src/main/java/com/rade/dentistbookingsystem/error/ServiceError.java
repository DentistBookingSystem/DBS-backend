package com.rade.dentistbookingsystem.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ServiceError {
    private String nameError;
    private String serviceType_id_Error;
    private String price_Error;
    private String imgError;
    private String priceError;

}
