package com.rade.dentistbookingsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountDTO implements Serializable {
    private long id;
    private String full_name;
    private Date date_of_birth;
    private int gender;
    private int district_id;
    private String phone;
    private String email;
    private int status;
}
