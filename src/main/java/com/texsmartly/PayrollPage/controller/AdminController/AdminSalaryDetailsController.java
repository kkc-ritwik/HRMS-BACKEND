package com.texsmartly.PayrollPage.controller.AdminController;

import com.texsmartly.PayrollPage.config.ApiVersionConfig;
import com.texsmartly.PayrollPage.dto.EmployeeSalaryDetailsDTO;
import com.texsmartly.PayrollPage.model.payroll.EmployeeSalaryDetails;
import com.texsmartly.PayrollPage.service.interf.EmployeeSalaryDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
@CrossOrigin("*")
@RequestMapping(ApiVersionConfig.API_VERSION + "/admin/salary")
@RestController
public class AdminSalaryDetailsController {
    @Autowired
    private EmployeeSalaryDetailService employeeSalaryDetailService;


    @GetMapping("/all-details")
    public ResponseEntity<List<EmployeeSalaryDetailsDTO>> getAllEmployeeSalaryDetails() {
        List<EmployeeSalaryDetailsDTO> allSalaryDetails = employeeSalaryDetailService.getAllEmployeeSalaryDetails();
        return ResponseEntity.ok(allSalaryDetails);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<EmployeeSalaryDetailsDTO> getSalaryDetailsByUserId(@PathVariable String userId) {
        EmployeeSalaryDetailsDTO salaryDetails = employeeSalaryDetailService.getSalaryDetailsByUserId(userId);
        return salaryDetails != null ? ResponseEntity.ok(salaryDetails) : ResponseEntity.notFound().build();
    }

}
