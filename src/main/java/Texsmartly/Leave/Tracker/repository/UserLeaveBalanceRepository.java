package Texsmartly.Leave.Tracker.repository;

import Texsmartly.Leave.Tracker.model.UserLeaveBalance;
import Texsmartly.Leave.Tracker.model.selectLeavePolicy.FixedEntitlementPolicy;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserLeaveBalanceRepository extends MongoRepository<UserLeaveBalance, String> {

    List<UserLeaveBalance> findByUserId(String userId);
    Optional<UserLeaveBalance> findByUserIdAndLeaveName(String userId, String leaveName);

}
