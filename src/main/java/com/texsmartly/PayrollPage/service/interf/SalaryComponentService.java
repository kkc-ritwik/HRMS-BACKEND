package com.texsmartly.PayrollPage.service.interf;

import com.texsmartly.PayrollPage.dto.SalaryComponentDTO;
import com.texsmartly.PayrollPage.model.settings.SalaryComponent;

import java.util.List;

public interface SalaryComponentService {
    SalaryComponentDTO createSalaryComponent(SalaryComponentDTO salaryComponentDTO);

    SalaryComponent getSalaryComponent(String id);


    List<SalaryComponent> getAllSalaryComponents();


    SalaryComponent getSalaryComponentByUserIdOrDefault(String userId);
}
