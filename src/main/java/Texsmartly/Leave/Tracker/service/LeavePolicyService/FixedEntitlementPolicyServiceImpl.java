package Texsmartly.Leave.Tracker.service.LeavePolicyService;

import Texsmartly.Leave.Tracker.dto.LeavePolicyDTO.FixedEntitlementPolicyDTO;
import Texsmartly.Leave.Tracker.exception.ResourceNotFoundException;
import Texsmartly.Leave.Tracker.model.selectLeavePolicy.FixedEntitlementPolicy;
import Texsmartly.Leave.Tracker.repository.LeavePolicy.FixedEntitlementPolicyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FixedEntitlementPolicyServiceImpl implements FixedEntitlementPolicyService{
    @Autowired
    private FixedEntitlementPolicyRepository fixedEntitlementPolicyRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public FixedEntitlementPolicyDTO createFixedEntitlementPolicy(FixedEntitlementPolicyDTO dto) {
        FixedEntitlementPolicy policyEntity = modelMapper.map(dto, FixedEntitlementPolicy.class);
        policyEntity.setStatus("PENDING");
        FixedEntitlementPolicy savedPolicy = fixedEntitlementPolicyRepository.save(policyEntity);
        return modelMapper.map(savedPolicy, FixedEntitlementPolicyDTO.class);
    }

    @Override
    public FixedEntitlementPolicyDTO getFixedEntitlementPolicyById(String id) {
        // Find the entity by ID
        FixedEntitlementPolicy policyEntity = fixedEntitlementPolicyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("FixedEntitlementPolicy", "id", id));

        // Return the entity mapped to DTO
        return modelMapper.map(policyEntity, FixedEntitlementPolicyDTO.class);
    }

    @Override
    public List<FixedEntitlementPolicyDTO> getAllFixedEntitlementPolicies() {
        // Find all entities and map them to DTOs
        return fixedEntitlementPolicyRepository.findAll().stream()
                .map(policy -> modelMapper.map(policy, FixedEntitlementPolicyDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public FixedEntitlementPolicyDTO updateFixedEntitlementPolicy(String id, FixedEntitlementPolicyDTO dto) {
        // Find the existing entity
        FixedEntitlementPolicy policyEntity = fixedEntitlementPolicyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("FixedEntitlementPolicy", "id", id));

        modelMapper.map(dto, policyEntity);

        FixedEntitlementPolicy updatedPolicy = fixedEntitlementPolicyRepository.save(policyEntity);

        return modelMapper.map(updatedPolicy, FixedEntitlementPolicyDTO.class);
    }

    @Override
    public void deleteFixedEntitlementPolicy(String id) {

        FixedEntitlementPolicy policyEntity = fixedEntitlementPolicyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("FixedEntitlementPolicy", "id", id));

        fixedEntitlementPolicyRepository.delete(policyEntity);
    }

    @Override
    public FixedEntitlementPolicyDTO adminLeaveAppoverOrReject(String id, String isApprover) {
        Optional<FixedEntitlementPolicy> policyOpt = fixedEntitlementPolicyRepository.findById(id);
        if (policyOpt.isPresent()) {
            FixedEntitlementPolicy existingPolicy = policyOpt.get();
            if(isApprover.equalsIgnoreCase("Approved")) {
                existingPolicy.setStatus("Approved");
            }else{
                existingPolicy.setStatus("Rejected");
            }
            return modelMapper.map(fixedEntitlementPolicyRepository.save(existingPolicy), FixedEntitlementPolicyDTO.class);
        }
        return null;
    }

    @Override
    public List<FixedEntitlementPolicyDTO> getApprovedFixedEntitlementPolicies() {
        return fixedEntitlementPolicyRepository.findByStatus("Approved").stream()
                .map(policy -> modelMapper.map(policy, FixedEntitlementPolicyDTO.class))
                .collect(Collectors.toList());
    }

}
