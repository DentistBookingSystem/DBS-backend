package com.rade.dentistbookingsystem.componentform;

import com.rade.dentistbookingsystem.model.DiscountDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DiscountServiceComponent {
    private DiscountDTO discountDTO;
    private int[] serviceIDList;


}
