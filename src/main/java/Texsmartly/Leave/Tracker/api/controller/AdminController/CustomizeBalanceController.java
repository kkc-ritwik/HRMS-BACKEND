package Texsmartly.Leave.Tracker.api.controller.AdminController;

import Texsmartly.Leave.Tracker.api.controller.CommonController.UserLeaveBalanceController;
import Texsmartly.Leave.Tracker.config.ApiVersionConfig;
import Texsmartly.Leave.Tracker.dto.UserLeaveBalanceDTO;
import Texsmartly.Leave.Tracker.service.UserLeaveBalanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiVersionConfig.API_VERSION + "/admin")

public class CustomizeBalanceController {
    @Autowired
    private UserLeaveBalanceService userLeaveBalanceService;
    private static final Logger logger = LoggerFactory.getLogger(UserLeaveBalanceController.class);
    @PutMapping("/customize-balance")
    public ResponseEntity<UserLeaveBalanceDTO> customizeBalance(
            @RequestParam String userId,
            @RequestParam String leaveName,
            @RequestParam int newAvailable,
            @RequestParam String reason) {

        UserLeaveBalanceDTO updatedBalance = userLeaveBalanceService.updateLeaveBalance(userId, leaveName, newAvailable, reason);
        return ResponseEntity.ok(updatedBalance);
    }

    @GetMapping("/all-users")
    public ResponseEntity<List<UserLeaveBalanceDTO>> getAllUsersWithLeaves() {
        List<UserLeaveBalanceDTO> allUsersWithLeaves = userLeaveBalanceService.getAllUsersWithLeaves();
        return ResponseEntity.ok(allUsersWithLeaves);
    }

    // New endpoint to get all leave balances by userId
    @GetMapping("/all-by-user")
    public ResponseEntity<List<UserLeaveBalanceDTO>> getAllByUserId(@RequestParam String userId) {
        List<UserLeaveBalanceDTO> leaveBalances = userLeaveBalanceService.getAllByUserId(userId);
        return ResponseEntity.ok(leaveBalances);
    }
}
