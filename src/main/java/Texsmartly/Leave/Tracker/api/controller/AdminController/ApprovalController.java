package Texsmartly.Leave.Tracker.api.controller.AdminController;

import Texsmartly.Leave.Tracker.config.ApiVersionConfig;
import Texsmartly.Leave.Tracker.dto.settings.ApprovalDTO;
import Texsmartly.Leave.Tracker.service.settings.ApprovalsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiVersionConfig.API_VERSION +"/admin/approvals")
public class ApprovalController {
    @Autowired
    private ApprovalsService approvalsService;

    @GetMapping
    public ResponseEntity<List<ApprovalDTO>> getApproval() {
        List<ApprovalDTO> approvalDTOList = approvalsService.getApproval();
        if (!approvalDTOList.isEmpty()) {
            return new ResponseEntity<>(approvalDTOList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getApprovalById(@PathVariable String id) {
        ApprovalDTO approvalDTO = approvalsService.getApprovalById(id);
        if (approvalDTO != null) {
            return new ResponseEntity<>(approvalDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>("Approval not found with id : " + id, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<ApprovalDTO> createApproval(@RequestBody @Valid ApprovalDTO approvalDTO) {
        ApprovalDTO saveApproval = approvalsService.createApproval(approvalDTO);
        if (saveApproval != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(saveApproval);
        }
        return ResponseEntity.internalServerError().build();
    }

    @PutMapping("/updated-status/{id}")
    public ResponseEntity<?> updateApprovalStatus(@PathVariable String id, @RequestParam Boolean status) {
        ApprovalDTO updatedApproval = approvalsService.updateApprovalStatus(id, status);
        if (updatedApproval != null) {
            return ResponseEntity.status(HttpStatus.OK).body(updatedApproval);
        }
        return new ResponseEntity<>("Approval not found with id : " + id, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteApproval(@PathVariable String id) {
        ApprovalDTO existingApproval = approvalsService.getApprovalById(id);
        if (existingApproval != null) {
            approvalsService.deleteApproval(id);
            return new ResponseEntity<String>("Approval deleted successfully.", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Approval not found with id: " + id, HttpStatus.NOT_FOUND);

    }

//    @GetMapping("/active")
//    public ResponseEntity<ApprovalDTO> getActiveApproval() {
//        ApprovalDTO approvalDTO = approvalsService.getActiveApproval();
//        if (approvalDTO != null) {
//            return ResponseEntity.ok(approvalDTO);
//        }
//        return ResponseEntity.notFound().build();
//    }


}
