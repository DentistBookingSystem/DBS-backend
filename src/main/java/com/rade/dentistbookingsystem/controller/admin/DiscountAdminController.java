package com.rade.dentistbookingsystem.controller.admin;


import com.rade.dentistbookingsystem.domain.Discount;
import com.rade.dentistbookingsystem.services.impl.DiscountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("rade/admin/discount")
public class DiscountAdminController {
    @Autowired
    DiscountServiceImpl discountSv;


    @GetMapping("page")
    public Page<Discount> discountListPage() {
        return discountSv.findAllWithPagination();
    }

    @GetMapping()
    public List<Discount> discountList() {
        return discountSv.findAll();
    }


}
