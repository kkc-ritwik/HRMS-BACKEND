package Texsmartly.Leave.Tracker.api.controller.CommonController;

import Texsmartly.Leave.Tracker.config.ApiVersionConfig;
import Texsmartly.Leave.Tracker.dto.settings.HolidayDTO;
import Texsmartly.Leave.Tracker.model.User;
import Texsmartly.Leave.Tracker.service.AuthService.UserService;
import Texsmartly.Leave.Tracker.service.settings.HolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(ApiVersionConfig.API_VERSION + "/common/holiday")
public class HolidayController {
    @Autowired
    private UserService userService;
    @Autowired
    private HolidayService holidayService;

    @GetMapping("/my-holidays")
    public ResponseEntity<List<HolidayDTO>> getMyHolidays(Principal principal) {
        if (principal == null) {
            throw new RuntimeException("User is not authenticated");
        }

        User user = userService.getUserByUsername(principal.getName());
        List<HolidayDTO> myHolidays = holidayService.getHolidaysForUser(user);
        return ResponseEntity.ok(myHolidays);
    }
}
