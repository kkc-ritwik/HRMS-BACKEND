package com.texsmartly.PayrollPage.service.impl;

import com.texsmartly.PayrollPage.dto.SalaryComponentDTO;
import com.texsmartly.PayrollPage.model.settings.SalaryComponent;
import com.texsmartly.PayrollPage.repository.settings.SalaryComponentRepository;
import com.texsmartly.PayrollPage.service.interf.SalaryComponentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaryComponentServiceImpl implements SalaryComponentService {
    @Autowired
    private SalaryComponentRepository salaryComponentRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public SalaryComponentDTO createSalaryComponent(SalaryComponentDTO salaryComponentDTO) {
        SalaryComponent salaryComponent = modelMapper.map(salaryComponentDTO, SalaryComponent.class);
        SalaryComponent salaryComponent1 = salaryComponentRepository.save(salaryComponent);
        return modelMapper.map(salaryComponent1, SalaryComponentDTO.class);
    }


    @Override
    public SalaryComponent getSalaryComponent(String id) {
        return salaryComponentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Salary Component not found"));
    }
    @Override
    public List<SalaryComponent> getAllSalaryComponents() {  // Implement this method
        return salaryComponentRepository.findAll();
    }

    @Override
    public SalaryComponent getSalaryComponentByUserIdOrDefault(String userId) {
        List<SalaryComponent> userSpecificComponents = salaryComponentRepository.findByUserId(userId);

        if (!userSpecificComponents.isEmpty()) {
            return userSpecificComponents.get(0); // Return the first component if available for the user
        } else {
            // If no user-specific component exists, fetch the default component
            return salaryComponentRepository.findFirstByOrderByCreatedDateDesc()
                    .orElseThrow(() -> new RuntimeException("Default Salary Component configuration not found"));
        }
    }

}
