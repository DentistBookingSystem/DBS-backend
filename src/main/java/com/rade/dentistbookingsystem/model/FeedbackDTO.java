package com.rade.dentistbookingsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FeedbackDTO implements Serializable {
    private int id;
    private int service_id;
    private int account_id;
    private Date time;
    private String content;
    private int status;

}
