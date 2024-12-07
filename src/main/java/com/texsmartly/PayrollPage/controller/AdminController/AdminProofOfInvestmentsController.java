package com.texsmartly.PayrollPage.controller.AdminController;

import com.texsmartly.PayrollPage.config.ApiVersionConfig;
import com.texsmartly.PayrollPage.dto.ProofOfInvestmentsDTO;
import com.texsmartly.PayrollPage.service.interf.ProofOfInvestmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiVersionConfig.API_VERSION + "/admin/proofs-of-investments")
@CrossOrigin("*")
public class AdminProofOfInvestmentsController {
    @Autowired
    private ProofOfInvestmentsService proofOfInvestmentsService;
    @GetMapping
    public ResponseEntity<List<ProofOfInvestmentsDTO>> getAllProofOfInvestments() {
        List<ProofOfInvestmentsDTO> investments = proofOfInvestmentsService.getAllProofOfInvestments();
        return ResponseEntity.ok(investments);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<ProofOfInvestmentsDTO> getProofOfInvestmentsByUserId(@PathVariable String userId) {
        ProofOfInvestmentsDTO proofOfInvestments = proofOfInvestmentsService.getProofOfInvestmentsByUserId(userId);
        return proofOfInvestments != null ? ResponseEntity.ok(proofOfInvestments) : ResponseEntity.notFound().build();
    }

}
