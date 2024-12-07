package Texsmartly.Leave.Tracker.service.settings;

import Texsmartly.Leave.Tracker.dto.settings.ApprovalDTO;
import Texsmartly.Leave.Tracker.model.settings.Approval;

import java.time.LocalDateTime;
import java.util.List;

public interface ApprovalsService {
    List<ApprovalDTO> getApproval();
    ApprovalDTO getApprovalById(String id);
    ApprovalDTO getActiveApproval();
    ApprovalDTO createApproval(ApprovalDTO approvalDTO);
    ApprovalDTO updateApprovalStatus(String id, Boolean status);
    void deleteApproval(String id);

    List<Approval> getApprovalsForFollowUp();
    void markFollowUpSent(String approvalId, LocalDateTime sentAt);
    Approval updateApproval(Approval approval);
}
