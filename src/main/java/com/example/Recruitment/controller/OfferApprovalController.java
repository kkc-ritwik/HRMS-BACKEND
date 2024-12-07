package com.example.Recruitment.controller;

import com.example.Recruitment.config.ApiVersionConfig;
import com.example.Recruitment.dto.OfferApprovalDTO;
import com.example.Recruitment.service.OfferApprovalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiVersionConfig.API_VERSION + "/offer-approvals")
@CrossOrigin("*")
public class OfferApprovalController {

    @Autowired
    private OfferApprovalService offerApprovalService;

    @PostMapping
    public ResponseEntity<?> createOfferApproval(@Valid @RequestBody OfferApprovalDTO offerApprovalDTO) {
        OfferApprovalDTO createdOffer = offerApprovalService.createOfferApproval(offerApprovalDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOffer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOfferApproval(
            @PathVariable String id,
            @Valid @RequestBody OfferApprovalDTO offerApprovalDTO) {
        OfferApprovalDTO updatedOffer = offerApprovalService.updateOfferApproval(id, offerApprovalDTO);
        return ResponseEntity.ok(updatedOffer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OfferApprovalDTO> getOfferApproval(@PathVariable String id) {
        OfferApprovalDTO offerApproval = offerApprovalService.getOfferApproval(id);
        return ResponseEntity.ok(offerApproval);
    }

    @GetMapping
    public ResponseEntity<List<OfferApprovalDTO>> getAllOfferApprovals() {
        List<OfferApprovalDTO> approvals = offerApprovalService.getAllOfferApprovals();
        return ResponseEntity.ok(approvals);
    }

    @GetMapping("/candidate/{candidateId}")
    public ResponseEntity<List<OfferApprovalDTO>> getOfferApprovalsByCandidateId(
            @PathVariable String candidateId) {
        List<OfferApprovalDTO> approvals = offerApprovalService.getOfferApprovalsByCandidateId(candidateId);
        return ResponseEntity.ok(approvals);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOfferApproval(@PathVariable String id) {
        offerApprovalService.deleteOfferApproval(id);
        return ResponseEntity.noContent().build(); // Use 204 status for delete operations
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateOfferStatus(
            @PathVariable String id,
            @RequestParam String status) {
        OfferApprovalDTO updatedStatus = offerApprovalService.updateOfferStatus(id, status);
        return ResponseEntity.ok(updatedStatus);
    }
}
