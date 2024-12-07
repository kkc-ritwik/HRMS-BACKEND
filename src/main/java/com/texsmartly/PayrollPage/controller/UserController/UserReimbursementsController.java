package com.texsmartly.PayrollPage.controller.UserController;
import com.texsmartly.PayrollPage.config.ApiVersionConfig;
import com.texsmartly.PayrollPage.dto.EmployeeReimbursementsDTO;
import com.texsmartly.PayrollPage.service.interf.EmployeeReimbursementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(ApiVersionConfig.API_VERSION + "/user/reimbursements")
public class UserReimbursementsController {
    @Autowired
    private EmployeeReimbursementsService employeeReimbursementsService;

    @PostMapping
    public ResponseEntity<EmployeeReimbursementsDTO> saveEmployeeReimbursement(
            @RequestBody EmployeeReimbursementsDTO employeeReimbursementsDTO,
            @RequestParam(value = "proofs", required = false) MultipartFile proofs,
            Principal principal) throws IOException {

        if (principal == null) {
            throw new RuntimeException("User is not authenticated");
        }

        employeeReimbursementsDTO.setUserId(principal.getName());

        if (proofs != null && !proofs.isEmpty()) {
            employeeReimbursementsDTO.setProofs(proofs.getBytes());
        }

        EmployeeReimbursementsDTO response = employeeReimbursementsService.saveEmployeeReimbursement(employeeReimbursementsDTO);
        return ResponseEntity.ok(response);
    }





}
