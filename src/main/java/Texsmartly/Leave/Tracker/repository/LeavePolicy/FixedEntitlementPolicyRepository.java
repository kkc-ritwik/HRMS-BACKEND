package Texsmartly.Leave.Tracker.repository.LeavePolicy;

import Texsmartly.Leave.Tracker.model.selectLeavePolicy.FixedEntitlementPolicy;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface FixedEntitlementPolicyRepository extends MongoRepository<FixedEntitlementPolicy, String> {
    List<FixedEntitlementPolicy> findByStatus(String status);
    List<FixedEntitlementPolicy> findByUserId(String userId);

    Optional<FixedEntitlementPolicy> findByLeaveName(String leaveName);
}
