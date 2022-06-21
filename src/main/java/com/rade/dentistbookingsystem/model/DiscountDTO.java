package com.rade.dentistbookingsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DiscountDTO  implements Serializable {
    private int id;
    private String name;
    private float percentage;
    private String description;
    private int status;
    private String startDate;
    private String endDate;
}
