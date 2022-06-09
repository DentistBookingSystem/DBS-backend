package com.rade.dentistbookingsystem.controller;

import com.rade.dentistbookingsystem.componentform.ServiceFeedback;
import com.rade.dentistbookingsystem.domain.Service;
import com.rade.dentistbookingsystem.services.FeedBackService;
import com.rade.dentistbookingsystem.services.ServiceSv;
import com.rade.dentistbookingsystem.services.ServiceTypeSv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("rade/service")
public class ServiceController {
    @Autowired
    ServiceSv serviceSv;
    @Autowired
    FeedBackService feedBackService;
    @Autowired
    ServiceTypeSv serviceTypeSv;
    @GetMapping("{id}")
    public List<ServiceFeedback> list(@PathVariable int id){
        short available = 1;
        Pageable pageable = PageRequest.of(0, 3, Sort.by("id").descending());
        List<Service> serviceList = serviceSv.findByServiceTypeIdAndStatus(id, available);
        List<ServiceFeedback> serviceFeedbackList = new ArrayList<>();
        for (Service service : serviceList) {
            serviceFeedbackList.add(new ServiceFeedback(
                    service,
//                    feedBackService.findByServiceIdAndStatus(service.getId(), available, pageable)
                    feedBackService.filterFeedback(null, available, service.getId(), null, pageable)
            ));

        }
        return serviceFeedbackList;
    }


}
