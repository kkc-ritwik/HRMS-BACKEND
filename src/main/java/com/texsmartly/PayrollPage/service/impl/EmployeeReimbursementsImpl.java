package com.texsmartly.PayrollPage.service.impl;

import com.texsmartly.PayrollPage.dto.EmployeeReimbursementsDTO;
import com.texsmartly.PayrollPage.model.payroll.EmployeeReimbursements;
import com.texsmartly.PayrollPage.model.settings.Reimbursement;
import com.texsmartly.PayrollPage.model.settings.SalaryComponent;
import com.texsmartly.PayrollPage.repository.payroll.EmployeeReimbursementsRepository;
import com.texsmartly.PayrollPage.service.interf.EmployeeReimbursementsService;
import com.texsmartly.PayrollPage.service.interf.SalaryComponentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class EmployeeReimbursementsImpl implements EmployeeReimbursementsService {

    @Autowired
    private EmployeeReimbursementsRepository employeeReimbursementsRepository;
    @Autowired
    private ClaimNumberGeneratorService claimNumberGeneratorService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private SalaryComponentService salaryComponentService;
//    @Override
//    public EmployeeReimbursementsDTO saveEmployeeReimbursement(EmployeeReimbursementsDTO employeeReimbursementsDTO) {
//        EmployeeReimbursements employeeReimbursements = modelMapper.map(employeeReimbursementsDTO, EmployeeReimbursements.class);
//        employeeReimbursements.setClaimNumber(claimNumberGeneratorService.generateClaimNumber());
//        // Set status and date information
//        employeeReimbursements.setStatus("Pending");
//        employeeReimbursements.setRequestDateInfo(); // Set the date fields
//
//        // Save to the repository
//        EmployeeReimbursements savedReimbursement = employeeReimbursementsRepository.save(employeeReimbursements);
//
//        return modelMapper.map(savedReimbursement, EmployeeReimbursementsDTO.class);
//    }

    @Override
    public EmployeeReimbursementsDTO saveEmployeeReimbursement(EmployeeReimbursementsDTO employeeReimbursementsDTO) {
        String userId = employeeReimbursementsDTO.getUserId();

        // Fetch SalaryComponent
        SalaryComponent salaryComponent = salaryComponentService.getSalaryComponentByUserIdOrDefault(userId);

        if (salaryComponent.getReimbursements() == null || salaryComponent.getReimbursements().isEmpty()) {
            throw new RuntimeException("No reimbursements found in Salary Component for user or default data.");
        }

        System.out.println("Available Reimbursements for User ID " + userId + ":");
        salaryComponent.getReimbursements().forEach(reimbursement ->
                System.out.println("Reimbursement Name: " + reimbursement.getReimbursementName())
        );

        Optional<Reimbursement> reimbursementOpt = salaryComponent.getReimbursements().stream()
                .filter(r -> r.getReimbursementName().equals(employeeReimbursementsDTO.getReimbursementName()))
                .findFirst();

        if (reimbursementOpt.isPresent()) {
            Reimbursement reimbursement = reimbursementOpt.get();
            employeeReimbursementsDTO.setMonthlyEligibleAmount(reimbursement.getMonthlyEligibleAmount());
            employeeReimbursementsDTO.setAnnualEligibleAmount(reimbursement.getAnnualEligibleAmount());
        } else {
            throw new RuntimeException("Reimbursement type '" + employeeReimbursementsDTO.getReimbursementName() +
                    "' not found in Salary Component for user or default data.");
        }

        // Generate a new claim number
        String claimNumber = claimNumberGeneratorService.generateClaimNumber();
        employeeReimbursementsDTO.setClaimNumber(claimNumber);

        // Map DTO to Entity and set other details
        EmployeeReimbursements employeeReimbursements = modelMapper.map(employeeReimbursementsDTO, EmployeeReimbursements.class);
        employeeReimbursements.setStatus("Pending");
        employeeReimbursements.setRequestDateInfo();

        // Save to the repository
        EmployeeReimbursements savedReimbursement = employeeReimbursementsRepository.save(employeeReimbursements);

        return modelMapper.map(savedReimbursement, EmployeeReimbursementsDTO.class);
    }


    @Override
    public List<EmployeeReimbursementsDTO> getAllEmployeeReimbursements() {
        List<EmployeeReimbursements> reimbursementsList = employeeReimbursementsRepository.findAll();
        return reimbursementsList.stream()
                .map(reimbursement -> modelMapper.map(reimbursement, EmployeeReimbursementsDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeReimbursementsDTO> getEmployeeReimbursementsByUser(String userId) {
        List<EmployeeReimbursements> reimbursementsList = employeeReimbursementsRepository.findByUserId(userId);
        return reimbursementsList.stream()
                .map(record -> modelMapper.map(record, EmployeeReimbursementsDTO.class))
                .collect(Collectors.toList());
    }

    // New method to update reimbursement status
    @Override
    public EmployeeReimbursementsDTO updateReimbursementStatus(String id, String status) {
        EmployeeReimbursements reimbursement = employeeReimbursementsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reimbursement not found"));

        reimbursement.setStatus(status);
        EmployeeReimbursements updatedReimbursement = employeeReimbursementsRepository.save(reimbursement);

        return modelMapper.map(updatedReimbursement, EmployeeReimbursementsDTO.class);
    }
    @Override
    public EmployeeReimbursementsDTO getReimbursementsByUserId(String userId) {
        return employeeReimbursementsRepository.findByUserId(userId).stream()
                .findFirst()
                .map(employeeReimbursements -> modelMapper.map(employeeReimbursements, EmployeeReimbursementsDTO.class))
                .orElse(null);
    }
}
