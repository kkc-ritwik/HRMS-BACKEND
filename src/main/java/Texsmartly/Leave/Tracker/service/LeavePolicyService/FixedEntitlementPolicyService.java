package Texsmartly.Leave.Tracker.service.LeavePolicyService;

import Texsmartly.Leave.Tracker.dto.LeavePolicyDTO.FixedEntitlementPolicyDTO;

import java.util.List;

public interface FixedEntitlementPolicyService {
    FixedEntitlementPolicyDTO createFixedEntitlementPolicy(FixedEntitlementPolicyDTO dto);
    FixedEntitlementPolicyDTO getFixedEntitlementPolicyById(String id);
    List<FixedEntitlementPolicyDTO> getAllFixedEntitlementPolicies();
    FixedEntitlementPolicyDTO updateFixedEntitlementPolicy(String id, FixedEntitlementPolicyDTO dto);
    void deleteFixedEntitlementPolicy(String id);


    FixedEntitlementPolicyDTO adminLeaveAppoverOrReject(String id, String isApprover);

    // New method to fetch approved policies
    List<FixedEntitlementPolicyDTO> getApprovedFixedEntitlementPolicies();
}
