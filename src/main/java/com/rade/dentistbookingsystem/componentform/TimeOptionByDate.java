package com.rade.dentistbookingsystem.componentform;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@AllArgsConstructor
@Data
public class TimeOptionByDate {
    String date;
    ArrayList<String> timeList;

    public TimeOptionByDate(String date, String time) {
        this.date = date;
        timeList = new ArrayList<>();
        timeList.add(time);
    }

    public TimeOptionByDate(String date) {
        this.date = date;
        timeList = new ArrayList<>();
    }
}
