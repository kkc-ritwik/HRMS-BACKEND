package Texsmartly.Leave.Tracker.service;

import Texsmartly.Leave.Tracker.dto.FormDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public interface FormService {




    List<FormDTO> getAllForms();


    List<FormDTO> getFormsByUserId(String userId);

//    @Transactional
//    FormDTO updateFormStatus(String formId, String status);

    FormDTO applyForm(FormDTO formDTO);

    @Transactional
    FormDTO updateFormStatus(String formId, String status);
}
