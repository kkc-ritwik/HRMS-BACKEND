package Texsmartly.Leave.Tracker.service.settings;

import Texsmartly.Leave.Tracker.dto.settings.EmployeeHolidayDTO;
import Texsmartly.Leave.Tracker.dto.settings.HolidayAssignmentDTO;
import Texsmartly.Leave.Tracker.dto.settings.HolidayDTO;
import Texsmartly.Leave.Tracker.model.User;
import Texsmartly.Leave.Tracker.model.settings.Holiday;

import java.util.List;

public interface HolidayService {
    HolidayDTO createHoliday(Holiday holiday);
    List<HolidayDTO> getAllHolidays();
    List<EmployeeHolidayDTO> getEmployeesByDepartmentLocationDivision(
            String department, String location, String division);
    void assignHolidayToEmployees(HolidayAssignmentDTO assignmentDTO);


    // Add this implementation to HolidayServiceImpl
    List<HolidayDTO> getHolidaysForUser(User user);
}
