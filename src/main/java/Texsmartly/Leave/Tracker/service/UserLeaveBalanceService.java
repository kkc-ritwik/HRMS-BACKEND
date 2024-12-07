package Texsmartly.Leave.Tracker.service;

import Texsmartly.Leave.Tracker.dto.UserLeaveBalanceDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserLeaveBalanceService {

    List<UserLeaveBalanceDTO> getApprovedUserLeaveBalances();
    List<UserLeaveBalanceDTO> getAllByUserId(String userId);  // New method
    List<UserLeaveBalanceDTO> getLeavesByUserId(String userId);

    List<UserLeaveBalanceDTO> getAllUsersWithLeaves();

    @Transactional
    UserLeaveBalanceDTO updateLeaveBalance(String userId, String leaveName, int newAvailable, String reason);
}
