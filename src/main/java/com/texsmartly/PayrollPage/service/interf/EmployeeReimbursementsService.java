package com.texsmartly.PayrollPage.service.interf;

import com.texsmartly.PayrollPage.dto.EmployeeReimbursementsDTO;

import java.util.List;

public interface EmployeeReimbursementsService {


    EmployeeReimbursementsDTO saveEmployeeReimbursement(EmployeeReimbursementsDTO employeeReimbursementsDTO);

    List<EmployeeReimbursementsDTO> getAllEmployeeReimbursements();

    List<EmployeeReimbursementsDTO> getEmployeeReimbursementsByUser(String userId);

    EmployeeReimbursementsDTO updateReimbursementStatus(String id, String status);


    EmployeeReimbursementsDTO getReimbursementsByUserId(String userId);
}
