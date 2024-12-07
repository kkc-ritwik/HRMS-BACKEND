package com.texsmartly.PayrollPage.controller.CommonController;
import com.texsmartly.PayrollPage.config.ApiVersionConfig;
import com.texsmartly.PayrollPage.model.payroll.EmployeeSalaryDetails;
import com.texsmartly.PayrollPage.service.interf.EmployeeSalaryDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
@RequestMapping(ApiVersionConfig.API_VERSION + "/common/salary")
@RestController
public class CommonSalaryDetailsController {

    @Autowired
    private EmployeeSalaryDetailService employeeSalaryDetailService;


    @PostMapping("/calculate")
    public ResponseEntity<EmployeeSalaryDetails> calculateEmployeeSalary(
            @RequestParam double annualCTC,  @RequestParam String financialYear,
            @RequestParam String userId
        ) {
        EmployeeSalaryDetails employeeSalaryDetails = employeeSalaryDetailService.calculateAndSaveEmployeeSalary(userId, annualCTC, financialYear);
        return ResponseEntity.ok(employeeSalaryDetails);
    }
}
