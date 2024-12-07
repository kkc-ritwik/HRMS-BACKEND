package com.texsmartly.PayrollPage.controller.AdminController;


import com.texsmartly.PayrollPage.config.ApiVersionConfig;
import com.texsmartly.PayrollPage.dto.CandidatesDTO;
import com.texsmartly.PayrollPage.dto.SalaryStructureDTO;
import com.texsmartly.PayrollPage.service.interf.CandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping(ApiVersionConfig.API_VERSION + "/admin/candidates")
public class AdminCandidateController {
    @Autowired
    private CandidateService candidatesService;

    @GetMapping
    public ResponseEntity<List<CandidatesDTO>> getAllCandidates() {
        return ResponseEntity.ok(candidatesService.getAllCandidates());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CandidatesDTO> getCandidateById(@PathVariable String id) {
        return ResponseEntity.ok(candidatesService.getCandidateById(id));
    }

    @PostMapping
    public ResponseEntity<CandidatesDTO> createCandidate(@RequestBody CandidatesDTO candidatesDTO) {
        return ResponseEntity.ok(candidatesService.createCandidate(candidatesDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CandidatesDTO> updateCandidate(@PathVariable String id, @RequestBody CandidatesDTO candidatesDTO) {
        return ResponseEntity.ok(candidatesService.updateCandidate(id, candidatesDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCandidate(@PathVariable String id) {
        candidatesService.deleteCandidate(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/salary")
    public ResponseEntity<CandidatesDTO> updateCandidateSalary(@PathVariable String id, @RequestBody List<SalaryStructureDTO> salaryStructures) {
        return ResponseEntity.ok(candidatesService.updateCandidateSalary(id, salaryStructures));
    }
}

