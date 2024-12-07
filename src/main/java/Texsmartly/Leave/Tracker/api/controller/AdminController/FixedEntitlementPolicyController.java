package Texsmartly.Leave.Tracker.api.controller.AdminController;

import Texsmartly.Leave.Tracker.config.ApiVersionConfig;
import Texsmartly.Leave.Tracker.dto.LeavePolicyDTO.FixedEntitlementPolicyDTO;
import Texsmartly.Leave.Tracker.service.LeavePolicyService.FixedEntitlementPolicyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(ApiVersionConfig.API_VERSION + "/common/entitlement-policies")
public class FixedEntitlementPolicyController {
    @Autowired
    private FixedEntitlementPolicyService service;

    @PostMapping("/created")
    public ResponseEntity<FixedEntitlementPolicyDTO> createFixedEntitlementPolicy(@Valid @RequestBody FixedEntitlementPolicyDTO dto, Principal principal) {
        if (principal == null) {
            throw new RuntimeException("User is not authenticated");
        }
        // Set the userId from the authenticated user
        dto.setUserId(principal.getName());
        FixedEntitlementPolicyDTO createdPolicy = service.createFixedEntitlementPolicy(dto);
        return new ResponseEntity<>(createdPolicy, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FixedEntitlementPolicyDTO> getFixedEntitlementPolicyById(@PathVariable String id) {
        FixedEntitlementPolicyDTO policy = service.getFixedEntitlementPolicyById(id);
        return ResponseEntity.ok(policy);
    }

    @GetMapping
    public ResponseEntity<List<FixedEntitlementPolicyDTO>> getAllFixedEntitlementPolicies() {
        List<FixedEntitlementPolicyDTO> policies = service.getAllFixedEntitlementPolicies();
        return ResponseEntity.ok(policies);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FixedEntitlementPolicyDTO> updateFixedEntitlementPolicy(@PathVariable String id,
                                                                                  @RequestBody FixedEntitlementPolicyDTO dto) {
        FixedEntitlementPolicyDTO updatedPolicy = service.updateFixedEntitlementPolicy(id, dto);
        return ResponseEntity.ok(updatedPolicy);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFixedEntitlementPolicy(@PathVariable String id) {
        service.deleteFixedEntitlementPolicy(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/toggle-status/{id}")
    public ResponseEntity<?> approveOrRejectLeave(@PathVariable String id, @RequestParam("isApprove") String isApprove) {
        FixedEntitlementPolicyDTO updatedPolicy = service.adminLeaveAppoverOrReject(id, isApprove);
        if (updatedPolicy != null) {
            return new ResponseEntity<>(updatedPolicy, HttpStatus.OK);
        }
        return new ResponseEntity<>("Policy not found or update failed", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/approved")
    public ResponseEntity<List<FixedEntitlementPolicyDTO>> getApprovedFixedEntitlementPolicies() {
        List<FixedEntitlementPolicyDTO> approvedPolicies = service.getApprovedFixedEntitlementPolicies();
        return ResponseEntity.ok(approvedPolicies);
    }

}
