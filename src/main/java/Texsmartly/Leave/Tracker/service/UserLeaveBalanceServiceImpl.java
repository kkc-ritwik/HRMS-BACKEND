package Texsmartly.Leave.Tracker.service;

import Texsmartly.Leave.Tracker.api.controller.CommonController.UserLeaveBalanceController;
import Texsmartly.Leave.Tracker.dto.UserLeaveBalanceDTO;
import Texsmartly.Leave.Tracker.model.UserLeaveBalance;
import Texsmartly.Leave.Tracker.model.selectLeavePolicy.FixedEntitlementPolicy;
import Texsmartly.Leave.Tracker.repository.UserLeaveBalanceRepository;
import Texsmartly.Leave.Tracker.repository.LeavePolicy.FixedEntitlementPolicyRepository;
import Texsmartly.Leave.Tracker.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserLeaveBalanceServiceImpl implements UserLeaveBalanceService {
    private static final Logger logger = LoggerFactory.getLogger(UserLeaveBalanceController.class);
    @Autowired
    private UserLeaveBalanceRepository userLeaveBalanceRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Autowired
    private FixedEntitlementPolicyRepository fixedEntitlementPolicyRepository;

    @Override
    public List<UserLeaveBalanceDTO> getApprovedUserLeaveBalances() {
        // Fetch all approved policies
        List<FixedEntitlementPolicy> approvedPolicies = fixedEntitlementPolicyRepository.findByStatus("Approved");
        logger.info("Approved Policies Count: {}", approvedPolicies.size());

        // Get the currently logged-in user’s ID (email) from the security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();

        // Fetch UserLeaveBalance records for the userId
        List<UserLeaveBalance> userLeaveBalances = userLeaveBalanceRepository.findByUserId(userId);

        // Map each approved policy to the user's leave balances
        return approvedPolicies.stream()
                .map(policy -> {
                    // Check if there's an existing balance entry for this policy's leave name
                    UserLeaveBalance leaveBalance = userLeaveBalances.stream()
                            .filter(existingBalance -> existingBalance.getLeaveName() != null &&
                                    existingBalance.getLeaveName().equals(policy.getLeaveName()))
                            .findFirst()
                            .orElseGet(() -> {
                                // Create a new leave balance if it doesn't exist for this policy
                                UserLeaveBalance newBalance = new UserLeaveBalance();
                                newBalance.setUserId(userId);  // Set userId directly
                                newBalance.setLeaveName(policy.getLeaveName());
                                newBalance.setAvailable(policy.getDays());  // Set available days from the policy
                                newBalance.setType(policy.getType());
                                newBalance.setBooked(0);  // Initialize booked leaves
                                userLeaveBalanceRepository.save(newBalance);  // Save to the repository
                                return newBalance;
                            });

                    // Map to UserLeaveBalanceDTO for the response
                    return modelMapper.map(leaveBalance, UserLeaveBalanceDTO.class);
                }).collect(Collectors.toList());
    }

    @Override
    public List<UserLeaveBalanceDTO> getAllByUserId(String userId) {
        // Fetch all UserLeaveBalance entries for the specified userId
        List<UserLeaveBalance> userLeaves = userLeaveBalanceRepository.findByUserId(userId);

        // Map each UserLeaveBalance entity to a DTO for the response
        return userLeaves.stream()
                .map(leave -> modelMapper.map(leave, UserLeaveBalanceDTO.class))
                .collect(Collectors.toList());
    }
    @Override
    public List<UserLeaveBalanceDTO> getLeavesByUserId(String userId) {
        List<UserLeaveBalance> leaveBalances = userLeaveBalanceRepository.findByUserId(userId);
        return leaveBalances.stream()
                .map(leaveBalance -> modelMapper.map(leaveBalance, UserLeaveBalanceDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserLeaveBalanceDTO> getAllUsersWithLeaves() {
        List<UserLeaveBalance> allLeaveBalances = userLeaveBalanceRepository.findAll();
        return allLeaveBalances.stream()
                .map(leaveBalance -> modelMapper.map(leaveBalance, UserLeaveBalanceDTO.class))
                .collect(Collectors.toList());
    }
@Transactional
@Override
public UserLeaveBalanceDTO updateLeaveBalance(String userId, String leaveName, int newAvailable, String reason) {
        UserLeaveBalance leaveBalance = userLeaveBalanceRepository.findByUserIdAndLeaveName(userId, leaveName)
                .orElseThrow(() -> new RuntimeException("Leave not found for user"));

        leaveBalance.setAvailable(newAvailable);
        leaveBalance.setDateOfUpdate(LocalDate.now());
        leaveBalance.setReason(reason);
        leaveBalance.setNewBalance(newAvailable);

        userLeaveBalanceRepository.save(leaveBalance);

        return modelMapper.map(leaveBalance, UserLeaveBalanceDTO.class);
    }
}