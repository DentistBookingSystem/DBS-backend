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
    private int appointmentId;
    private String content;
    private String time;
    private int status;
}
