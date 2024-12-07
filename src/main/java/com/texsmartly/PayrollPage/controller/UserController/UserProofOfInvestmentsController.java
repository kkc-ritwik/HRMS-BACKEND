package com.texsmartly.PayrollPage.controller.UserController;
import com.texsmartly.PayrollPage.config.ApiVersionConfig;
import com.texsmartly.PayrollPage.dto.ProofOfInvestmentsDTO;
import com.texsmartly.PayrollPage.service.interf.ProofOfInvestmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(ApiVersionConfig.API_VERSION + "/user/proofs-of-investments")
public class UserProofOfInvestmentsController {
    @Autowired
    private ProofOfInvestmentsService proofOfInvestmentsService;


    // POST method to create a new proof of investments
    @PostMapping
    public ResponseEntity<ProofOfInvestmentsDTO> saveProofOfInvestments(
            @RequestBody ProofOfInvestmentsDTO proofOfInvestmentsDTO,
            Principal principal) {
        if (principal == null) {
            throw new RuntimeException("User is not authenticated");
        }
        proofOfInvestmentsDTO.setUserId(principal.getName());
        ProofOfInvestmentsDTO response = proofOfInvestmentsService.saveProofOfInvestments(proofOfInvestmentsDTO);
        return ResponseEntity.ok(response);
    }
    // GET method to retrieve proofs of investments by user ID
    @GetMapping("/user")
    public ResponseEntity<List<ProofOfInvestmentsDTO>> getProofOfInvestmentsByUser(Principal principal) {
        if (principal == null) {
            throw new RuntimeException("User is not authenticated");
        }
        List<ProofOfInvestmentsDTO> investmentsDTOS = proofOfInvestmentsService.getProofOfInvestmentsByUser(principal.getName());
        return ResponseEntity.ok(investmentsDTOS);
    }
}
