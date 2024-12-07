package Texsmartly.Leave.Tracker.service.settings;

import Texsmartly.Leave.Tracker.dto.settings.EmployeeHolidayDTO;
import Texsmartly.Leave.Tracker.dto.settings.HolidayAssignmentDTO;
import Texsmartly.Leave.Tracker.dto.settings.HolidayDTO;
import Texsmartly.Leave.Tracker.model.User;
import Texsmartly.Leave.Tracker.model.settings.Holiday;
import Texsmartly.Leave.Tracker.model.settings.HolidayAssignment;
import Texsmartly.Leave.Tracker.repository.UserRepository;
import Texsmartly.Leave.Tracker.repository.settings.HolidayAssignmentRepository;
import Texsmartly.Leave.Tracker.repository.settings.HolidayRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class HolidayServiceImpl implements HolidayService{
    @Autowired
    private HolidayRepository holidayRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository usersRepository;
    @Autowired
    private HolidayAssignmentRepository holidayAssignmentRepository;

    private static final Logger logger = LoggerFactory.getLogger(HolidayServiceImpl.class);

    @Override
    public HolidayDTO createHoliday(Holiday holidayDTO) {
        Holiday holiday = modelMapper.map(holidayDTO, Holiday.class);
        holiday.setId(UUID.randomUUID().toString());
        holiday.setCreatedAt(LocalDateTime.now());
        Holiday savedHoliday = holidayRepository.save(holiday);
        return modelMapper.map(savedHoliday, HolidayDTO.class);
    }

    @Override
    public List<HolidayDTO> getAllHolidays() {
        List<Holiday> holidays = holidayRepository.findAll();
        return holidays.stream()
                .map(holiday -> modelMapper.map(holiday, HolidayDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeHolidayDTO> getEmployeesByDepartmentLocationDivision(
            String department, String location, String division) {



        List<User> employees = usersRepository.findByDepartmentOrLocationOrDivision(
                department, location, division);



        List<EmployeeHolidayDTO> responseList = new ArrayList<>();

        for (User employee : employees) {
            EmployeeHolidayDTO dto = new EmployeeHolidayDTO();
            dto.setUserId(employee.getId());
            dto.setEmail(employee.getEmail());
            dto.setDepartment(employee.getDepartment());
            dto.setLocation(employee.getLocation());
            dto.setDivision(employee.getDivision());

            List<HolidayAssignment> assignments = holidayAssignmentRepository
                    .findByUserIdOrderByCreatedAtDesc(employee.getId());

            if (!assignments.isEmpty()) {
                HolidayAssignment latestAssignment = assignments.get(0);
                dto.setCurrentHolidayId(latestAssignment.getHolidayId());
                dto.setCurrentHolidayName(latestAssignment.getHolidayName());
                dto.setHolidayDate(latestAssignment.getHolidayDate());
                dto.setCreatedAt(latestAssignment.getCreatedAt());
            }

            responseList.add(dto);
        }

        return responseList;
    }

    @Override
    public void assignHolidayToEmployees(HolidayAssignmentDTO assignmentDTO) {
        List<User> employees = usersRepository.findByDepartmentOrLocationOrDivision(
                assignmentDTO.getDepartment(),
                assignmentDTO.getLocation(),
                assignmentDTO.getDivision()
        );

        for (User employee : employees) {
            HolidayAssignment assignment = new HolidayAssignment();
            assignment.setId(UUID.randomUUID().toString());
            assignment.setHolidayId(assignmentDTO.getHolidayId());
            assignment.setHolidayName(assignmentDTO.getHolidayName());
            assignment.setUserId(employee.getId());
            assignment.setHolidayDate(assignmentDTO.getHolidayDate());
            assignment.setDescription(assignmentDTO.getDescription());
            assignment.setCreatedAt(LocalDateTime.now());

            holidayAssignmentRepository.save(assignment);

            logger.info("Holiday {} assigned to employee {} of department: {}, location: {}, division: {} for date {}",
                    assignmentDTO.getHolidayName(),
                    employee.getEmail(),
                    employee.getDepartment(),
                    employee.getLocation(),
                    employee.getDivision(),
                    assignmentDTO.getHolidayDate());
        }
    }

   // Add this implementation to HolidayServiceImpl
   @Override
   public List<HolidayDTO> getHolidaysForUser(User user) {
       // Find all holidays where user's department/location/division matches
       List<Holiday> allHolidays = holidayRepository.findAll();

       return allHolidays.stream()
               .filter(holiday ->
                       (holiday.getDepartments() != null && holiday.getDepartments().contains(user.getDepartment())) ||
                               (holiday.getLocations() != null && holiday.getLocations().contains(user.getLocation())) ||
                               (holiday.getDivisions() != null && holiday.getDivisions().contains(user.getDivision()))
               )
               .map(holiday -> modelMapper.map(holiday, HolidayDTO.class))
               .collect(Collectors.toList());
   }

}
