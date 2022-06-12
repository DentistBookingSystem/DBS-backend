package com.rade.dentistbookingsystem.componentform;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@AllArgsConstructor
@Data
public class ShiftBookedByDate {
    Date date;
    ArrayList<Integer> shiftList;

    public ShiftBookedByDate(Date date, int shift) {
        this.date = date;
        shiftList = new ArrayList<>();
        shiftList.add(shift);
    }
}
