package com.texsmartly.PayrollPage.controller.AdminController;

import com.texsmartly.PayrollPage.config.ApiVersionConfig;
import com.texsmartly.PayrollPage.dto.SalaryComponentDTO;
import com.texsmartly.PayrollPage.model.payroll.EmployeeSalaryDetails;
import com.texsmartly.PayrollPage.model.settings.SalaryComponent;
import com.texsmartly.PayrollPage.service.interf.EmployeeSalaryDetailService;
import com.texsmartly.PayrollPage.service.interf.SalaryComponentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
@RequestMapping(ApiVersionConfig.API_VERSION + "/admin/salaryComponents")
@RestController
public class AdminSalaryComponentController {
    @Autowired
    private SalaryComponentService salaryComponentService;
    @Autowired
    private EmployeeSalaryDetailService employeeSalaryDetailService;
@PostMapping
    public ResponseEntity<SalaryComponentDTO> createSalaryComponent(@Valid @RequestBody SalaryComponentDTO salaryComponentDTO, Principal principal) {
        if (principal == null) {
            throw new RuntimeException("User is not authenticated");
        }
        salaryComponentDTO.setUserId(principal.getName());
        SalaryComponentDTO response = salaryComponentService.createSalaryComponent(salaryComponentDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public List<SalaryComponent> getAllSalaryComponents() {
        return salaryComponentService.getAllSalaryComponents();
    }


    @GetMapping("/{id}")
    public ResponseEntity<SalaryComponent> getSalaryComponent(@PathVariable String id) {
        return ResponseEntity.ok(salaryComponentService.getSalaryComponent(id));
    }





}
