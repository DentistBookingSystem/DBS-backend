package com.rade.dentistbookingsystem.componentform;

import com.rade.dentistbookingsystem.domain.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AccountAndViolationTimes {
    private Account account;
    private int violation_times;
}
