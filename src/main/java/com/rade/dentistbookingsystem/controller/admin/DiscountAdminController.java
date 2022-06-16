package com.rade.dentistbookingsystem.controller.admin;


import com.rade.dentistbookingsystem.domain.Discount;
import com.rade.dentistbookingsystem.model.DiscountDTO;
import com.rade.dentistbookingsystem.services.DiscountService;
import com.rade.dentistbookingsystem.services.DiscountSvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("rade/admin/discount")
public class DiscountAdminController {
    @Autowired
    DiscountService discount;
    @Autowired
    DiscountSvService discountSv;


    @GetMapping("page")
    public Page<Discount> discountListPage() {
        return discount.findAllWithPagination();
    }

    @GetMapping("list")
    public List<Discount> discountList() {
        return discount.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<?> addDiscount(@RequestPart DiscountDTO discountDTO) {
        try {
            discount.addDiscount(discountDTO);
            return ResponseEntity.ok(discount.addDiscount(discountDTO));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();

    }


}
