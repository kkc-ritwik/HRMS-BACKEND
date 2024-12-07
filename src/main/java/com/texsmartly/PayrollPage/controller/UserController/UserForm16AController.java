package com.texsmartly.PayrollPage.controller.UserController;

import com.texsmartly.PayrollPage.config.ApiVersionConfig;
import com.texsmartly.PayrollPage.dto.Form16ADTO;
import com.texsmartly.PayrollPage.model.payroll.Form16A;
import com.texsmartly.PayrollPage.service.interf.Form16Aservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping(ApiVersionConfig.API_VERSION+"/user/form16a")
public class UserForm16AController {
    @Autowired
    private Form16Aservice form16Aservice;

    @PostMapping("/submit")
    public ResponseEntity<Form16A> submitForm(@RequestBody Form16ADTO form16ADto) {
        Form16A form16A = form16Aservice.saveForm16A(form16ADto);
        return ResponseEntity.ok(form16A);
    }
    @GetMapping("/{id}")
    public Form16ADTO getForm16AById(@PathVariable String id) {
        return form16Aservice.getForm16AById(id);
    }

    // Get all forms
    @GetMapping
    public List<Form16ADTO> getAllForm16A() {
        return form16Aservice.getAllForm16A();
    }
}
