package com.texsmartly.PayrollPage.controller.AdminController;
import com.texsmartly.PayrollPage.config.ApiVersionConfig;
import com.texsmartly.PayrollPage.dto.EmployeeReimbursementsDTO;
import com.texsmartly.PayrollPage.dto.EmployeeSalaryDetailsDTO;
import com.texsmartly.PayrollPage.service.interf.EmployeeReimbursementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
@RestController
@RequestMapping(ApiVersionConfig.API_VERSION + "/admin/reimbursements")
public class AdminReimbursementsController {

    @Autowired
    private EmployeeReimbursementsService employeeReimbursementsService;
    @GetMapping
    public ResponseEntity<List<EmployeeReimbursementsDTO>> getAllEmployeeReimbursements() {
        List<EmployeeReimbursementsDTO> reimbursements = employeeReimbursementsService.getAllEmployeeReimbursements();
        return ResponseEntity.ok(reimbursements);
    }
    // PUT method to update reimbursement status
    @PutMapping("/{id}/status")
    public ResponseEntity<EmployeeReimbursementsDTO> updateReimbursementStatus(
            @PathVariable String id,
            @RequestParam String status) {

        EmployeeReimbursementsDTO updatedReimbursement = employeeReimbursementsService.updateReimbursementStatus(id, status);
        return ResponseEntity.ok(updatedReimbursement);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<EmployeeReimbursementsDTO> getReimbursementsByUserId(@PathVariable String userId) {
        EmployeeReimbursementsDTO reimbursements = employeeReimbursementsService.getReimbursementsByUserId(userId);
        return reimbursements != null ? ResponseEntity.ok(reimbursements) : ResponseEntity.notFound().build();
    }
}
