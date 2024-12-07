package Texsmartly.Leave.Tracker.api.controller.AdminController;

import Texsmartly.Leave.Tracker.config.ApiVersionConfig;
import Texsmartly.Leave.Tracker.dto.settings.EmployeeHolidayDTO;
import Texsmartly.Leave.Tracker.dto.settings.HolidayAssignmentDTO;
import Texsmartly.Leave.Tracker.dto.settings.HolidayDTO;
import Texsmartly.Leave.Tracker.model.settings.Holiday;
import Texsmartly.Leave.Tracker.service.settings.HolidayService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(ApiVersionConfig.API_VERSION +"/admin/holiday")
public class AdminHolidayController {
    @Autowired
    private HolidayService holidayService;

    @PostMapping
    public ResponseEntity<HolidayDTO> createHoliday(@Valid @RequestBody Holiday holiday, Principal principal) {
        if (principal == null) {
            throw new RuntimeException("User is not authenticated");
        }

        HolidayDTO createdHoliday = holidayService.createHoliday(holiday);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdHoliday);
    }

    @GetMapping
    public ResponseEntity<List<HolidayDTO>> getAllHolidays() {
        List<HolidayDTO> holidays = holidayService.getAllHolidays();
        return ResponseEntity.ok(holidays);
    }
    //To get employees by department,location,division
    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeHolidayDTO>> getEmployeesByDepartmentLocationDivision(
            @RequestParam String department,
            @RequestParam String location,
            @RequestParam String division) {
        List<EmployeeHolidayDTO> employees = holidayService
                .getEmployeesByDepartmentLocationDivision(department, location, division);
        return ResponseEntity.ok(employees);
    }
    //assign holiday to employee
    @PostMapping("/assign")
    public ResponseEntity<Void> assignHoliday(@Valid @RequestBody HolidayAssignmentDTO assignmentDTO) {
        holidayService.assignHolidayToEmployees(assignmentDTO);
        return ResponseEntity.ok().build();
    }


}
