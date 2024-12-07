package Texsmartly.Leave.Tracker.service;

import Texsmartly.Leave.Tracker.api.controller.CommonController.FormController;
import Texsmartly.Leave.Tracker.dto.FormDTO;
import Texsmartly.Leave.Tracker.model.Form;
import Texsmartly.Leave.Tracker.model.UserLeaveBalance;
import Texsmartly.Leave.Tracker.model.selectLeavePolicy.FixedEntitlementPolicy;
import Texsmartly.Leave.Tracker.repository.FormRepository;
import Texsmartly.Leave.Tracker.repository.LeavePolicy.FixedEntitlementPolicyRepository;
import Texsmartly.Leave.Tracker.repository.UserLeaveBalanceRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FormServiceImpl implements   FormService{
    private static final Logger logger = LoggerFactory.getLogger(FormController.class);
    @Autowired
    private FormRepository formRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserLeaveBalanceRepository userLeaveBalanceRepository;
    @Autowired
    private FixedEntitlementPolicyRepository fixedEntitlementPolicyRepository;
    @Override
    public FormDTO applyForm(FormDTO formDTO){
        Form form = modelMapper.map(formDTO, Form.class);

        form.setStatus("PENDING");
        // Set the date of request
        form.setDateOfRequest();

        // Calculate the period
        form.calculatePeriod();

        Form form1 = formRepository.save(form);
        return modelMapper.map(form1,FormDTO.class);
    }
@Override
public List<FormDTO> getAllForms() {
        return formRepository.findAll().stream()
                .map(form -> modelMapper.map(form, FormDTO.class))
                .collect(Collectors.toList());
    }
@Override
    public List<FormDTO> getFormsByUserId(String userId) {
        return formRepository.findByUserId(userId).stream()
                .map(form -> modelMapper.map(form, FormDTO.class))
                .collect(Collectors.toList());
    }


    @Transactional
    @Override
    public FormDTO updateFormStatus(String formId, String status) {
        logger.info("Updating form status for Form ID: {} with Status: {}", formId, status);

        Optional<Form> formOptional = formRepository.findById(formId);
        if (formOptional.isPresent()) {
            Form form = formOptional.get();
            form.setStatus(status);
            form.setDateOfApproval();

            if ("APPROVED".equalsIgnoreCase(status)) {
                String leaveName = form.getLeaveName();
                String userId = form.getUserId();
                logger.info("Approving leave for User ID: {}, Leave Name: {}", userId, leaveName);

                // Check if a UserLeaveBalance exists; if not, create a new entry
                UserLeaveBalance userLeaveBalance = userLeaveBalanceRepository.findByUserIdAndLeaveName(userId, leaveName)
                        .orElseGet(() -> {
                            Optional<FixedEntitlementPolicy> policyOptional =
                                    fixedEntitlementPolicyRepository.findByLeaveName(leaveName);
                            if (policyOptional.isPresent()) {
                                FixedEntitlementPolicy policy = policyOptional.get();
                                UserLeaveBalance newBalance = new UserLeaveBalance();
                                newBalance.setUserId(userId);
                                newBalance.setLeaveName(policy.getLeaveName());
                                newBalance.setAvailable(policy.getDays());
                                newBalance.setBooked(0);
                                return userLeaveBalanceRepository.save(newBalance);
                            } else {
                                throw new RuntimeException("No leave found for User with leave name: " + leaveName);
                            }
                        });

                userLeaveBalance.setAvailable(userLeaveBalance.getAvailable() - form.getPeriod());
                userLeaveBalance.setBooked(userLeaveBalance.getBooked() + form.getPeriod());
                userLeaveBalanceRepository.save(userLeaveBalance);

                logger.info("Leave balances updated: Available = {}, Booked = {}", userLeaveBalance.getAvailable(), userLeaveBalance.getBooked());
            }

            formRepository.save(form);
            logger.info("Form status updated successfully for Form ID: {}", formId);
            return modelMapper.map(form, FormDTO.class);
        } else {
            throw new RuntimeException("Form not found with Form ID: " + formId);
        }
    }

}
