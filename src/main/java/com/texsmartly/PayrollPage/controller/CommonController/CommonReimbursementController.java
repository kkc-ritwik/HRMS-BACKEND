package com.texsmartly.PayrollPage.controller.CommonController;

import com.texsmartly.PayrollPage.config.ApiVersionConfig;
import com.texsmartly.PayrollPage.dto.EmployeeReimbursementsDTO;
import com.texsmartly.PayrollPage.service.interf.EmployeeReimbursementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(ApiVersionConfig.API_VERSION + "/user/reimbursements")
@CrossOrigin("*")
public class CommonReimbursementController {
    @Autowired
    private EmployeeReimbursementsService employeeReimbursementsService;
    @GetMapping("/user")
    public ResponseEntity<List<EmployeeReimbursementsDTO>> getEmployeeReimbursementsByUser(Principal principal) {
        if (principal == null) {
            throw new RuntimeException("User is not authenticated");
        }
        List<EmployeeReimbursementsDTO> reimbursementsDTOS = employeeReimbursementsService.getEmployeeReimbursementsByUser(principal.getName());
        return ResponseEntity.ok(reimbursementsDTOS);
    }

}
