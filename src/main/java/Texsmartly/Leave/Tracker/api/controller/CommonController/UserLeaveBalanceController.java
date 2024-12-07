package Texsmartly.Leave.Tracker.api.controller.CommonController;

import Texsmartly.Leave.Tracker.config.ApiVersionConfig;
import Texsmartly.Leave.Tracker.dto.UserLeaveBalanceDTO;
import Texsmartly.Leave.Tracker.service.UserLeaveBalanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(ApiVersionConfig.API_VERSION + "/common/leave-balance")
@Validated
public class UserLeaveBalanceController {
    @Autowired
    private UserLeaveBalanceService userLeaveBalanceService;
    private static final Logger logger = LoggerFactory.getLogger(UserLeaveBalanceController.class);


    @GetMapping
    public ResponseEntity<List<UserLeaveBalanceDTO>> getApprovedUserLeaveBalances() {
        List<UserLeaveBalanceDTO> leaveBalances = userLeaveBalanceService.getApprovedUserLeaveBalances();
        return ResponseEntity.ok(leaveBalances);
    }

    @GetMapping("/user")
    public ResponseEntity<List<UserLeaveBalanceDTO>> getLeavesByUser(Principal principal) {
        List<UserLeaveBalanceDTO> leaveBalances = userLeaveBalanceService.getLeavesByUserId(principal.getName());
        return ResponseEntity.ok(leaveBalances);
    }


}
