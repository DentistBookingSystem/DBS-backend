package com.rade.dentistbookingsystem.services.impl;

import com.rade.dentistbookingsystem.domain.Discount;
import com.rade.dentistbookingsystem.model.DiscountDTO;
import com.rade.dentistbookingsystem.repository.DiscountRepo;
import com.rade.dentistbookingsystem.services.AccountService;
import com.rade.dentistbookingsystem.services.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@Service
public class DiscountServiceImpl implements DiscountService {
    DiscountRepo discountRepo;
    @Autowired
    AccountService accountService;

    public DiscountServiceImpl(DiscountRepo discountRepo) {
        this.discountRepo = discountRepo;
    }

    public Discount findAvailableByServiceId(Integer id) {
        return discountRepo.findAvailableByServiceId(id);
    }


    @Override
    public List<Discount> findAll() {
        return discountRepo.findAll();
    }


    @Override
    public Page<Discount> findAll(Pageable pageable) {
        return discountRepo.findAll(pageable);
    }


    @Override
    public Page<Discount> findAllWithPagination() {
        return discountRepo.findAll(PageRequest.of(0, 5));
    }


    @Override
    public Optional<Discount> findById(Integer integer) {
        return discountRepo.findById(integer);
    }


    @Override
    public Discount addDiscount(DiscountDTO discountDTO) throws ParseException {
        Discount discount = new Discount();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        discount.setName(discountDTO.getName());
        discount.setStatus(discountDTO.getStatus());
        discount.setPercentage(discountDTO.getPercentage());
        discount.setDescription(discountDTO.getDescription());
        discount.setAccount(accountService.findById((discountDTO.getAccount_id())).orElseThrow(() -> new RuntimeException("Account not found")));
        discount.setStart_date(dateFormat.parse(discountDTO.getStart_date()));
        discount.setEnd_date(dateFormat.parse(discountDTO.getEnd_date()));
        return discountRepo.save(discount);
    }
}
