package Texsmartly.Leave.Tracker.api.controller.CommonController;

import Texsmartly.Leave.Tracker.config.ApiVersionConfig;
import Texsmartly.Leave.Tracker.dto.FormDTO;
import Texsmartly.Leave.Tracker.service.FormService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
@RestController
@RequestMapping(ApiVersionConfig.API_VERSION + "/common/form")
public class FormController {

@Autowired
private FormService formService;
    private static final Logger logger = LoggerFactory.getLogger(FormController.class);
    @PostMapping("/apply")
    public ResponseEntity<FormDTO> applyForm(@Valid @RequestBody FormDTO formDTO, Principal principal) {
        if (principal == null) {
            throw new RuntimeException("User is not authenticated");
        }
        // Set the userId from the authenticated user
        formDTO.setUserId(principal.getName());
        FormDTO appliedForm = formService.applyForm(formDTO);
        return new ResponseEntity<>(appliedForm, HttpStatus.CREATED);
    }



    // Get Forms for the logged-in user
    @GetMapping("/user")
    public ResponseEntity<List<FormDTO>> getUserForms(Principal principal) {
        if (principal == null) {
            throw new RuntimeException("User is not authenticated");
        }
        List<FormDTO> userForms = formService.getFormsByUserId(principal.getName());
        return ResponseEntity.ok(userForms);
    }

    //nicche ka dono api admin form controller se aay hai.
    // Update Form Status
    @PutMapping("/{formId}")
    public ResponseEntity<FormDTO> updateFormStatus(
            @PathVariable("formId") String formId,
            @RequestParam (name = "status")String status,
            Principal principal) {
        String username = principal.getName(); // Get the username of the authenticated admin
        logger.info("Admin user: {} is updating status for formId: {}, with status: {}", username, formId, status);

        try {
            // Update form status without checking user ID, as this is an admin operation
            FormDTO updatedForm = formService.updateFormStatus(formId, status);
            logger.info("Successfully updated status for formId: {} by admin user: {}", formId, username);
            return ResponseEntity.ok(updatedForm);
        } catch (RuntimeException e) {
            logger.error("Error updating form status for formId: {}: {}", formId, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            logger.error("Unexpected error while updating form status for formId: {}: {}", formId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<FormDTO>> getAllForms() {
        List<FormDTO> allForms = formService.getAllForms();
        return ResponseEntity.ok(allForms);
    }


}
