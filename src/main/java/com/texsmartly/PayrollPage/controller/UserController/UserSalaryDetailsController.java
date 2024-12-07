package com.texsmartly.PayrollPage.controller.UserController;

import com.texsmartly.PayrollPage.config.ApiVersionConfig;
import com.texsmartly.PayrollPage.dto.EmployeeSalaryDetailsDTO;
import com.texsmartly.PayrollPage.model.payroll.EmployeeSalaryDetails;
import com.texsmartly.PayrollPage.service.interf.EmployeeSalaryDetailService;
import com.texsmartly.PayrollPage.service.interf.SalaryComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
@RequestMapping(ApiVersionConfig.API_VERSION + "/common/salary")
@RestController
public class UserSalaryDetailsController {
    @Autowired
    private SalaryComponentService salaryComponentService;
    @Autowired
    private EmployeeSalaryDetailService employeeSalaryDetailService;
    @GetMapping("/details")
    public ResponseEntity<List<EmployeeSalaryDetailsDTO>> getEmployeeSalaryDetails(Principal principal) {
        if (principal == null) {
            throw new RuntimeException("User is not authenticated");
        }
        String userId = principal.getName();
        List<EmployeeSalaryDetailsDTO> salaryDetails = employeeSalaryDetailService.getEmployeeSalaryDetails(userId);
        return ResponseEntity.ok(salaryDetails);
    }


}
