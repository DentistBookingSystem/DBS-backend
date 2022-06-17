package com.rade.dentistbookingsystem.controller.admin;


import com.rade.dentistbookingsystem.componentform.DiscountServiceComponent;
import com.rade.dentistbookingsystem.domain.Discount;
import com.rade.dentistbookingsystem.model.DiscountServiceDTO;
import com.rade.dentistbookingsystem.services.DiscountService;
import com.rade.dentistbookingsystem.services.DiscountSvService;
import com.rade.dentistbookingsystem.services.ServiceSv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("rade/admin/discount")
public class DiscountAdminController {
    @Autowired
    DiscountService discount;
    @Autowired
    DiscountSvService discountSv;
    @Autowired
    ServiceSv service;

    @GetMapping("page")
    public Page<Discount> discountListPage() {
        return discount.findAllWithPagination();
    }

    @GetMapping("list")
    public List<Discount> discountList() {
        return discount.findAll();
    }

    @GetMapping("{id}")
    public Optional<Discount> getDiscount(@PathVariable int id) {
        return discount.findById(id);
    }

    @PostMapping("/add")
    // @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> addDiscount(@RequestBody DiscountServiceComponent discountServiceComponent) {
        try {

            int discountID = discount.addDiscount(discountServiceComponent.getDiscountDTO()).getId();
            int serviceList[] = discountServiceComponent.getServiceIDList();
            if (serviceList != null) {
                System.out.println(discountID);
                for (int i = 0; i < serviceList.length; i++) {
                    DiscountServiceDTO discountServiceDTO = new DiscountServiceDTO();
                    discountServiceDTO.setDiscount_id(discountID);
                    System.out.println(serviceList[i]);
                    discountServiceDTO.setService_id(serviceList[i]);
                    return ResponseEntity.ok(discountSv.addServiceDiscount(discountServiceDTO));
                }

            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();

    }


}
