package com.rade.dentistbookingsystem.componentform;

import com.rade.dentistbookingsystem.domain.Feedback;
import com.rade.dentistbookingsystem.domain.Service;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ServiceFeedback {
    private Service service;
    private List<Feedback> feedbackList;
}
