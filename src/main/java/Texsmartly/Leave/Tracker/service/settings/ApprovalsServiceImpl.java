package Texsmartly.Leave.Tracker.service.settings;

import Texsmartly.Leave.Tracker.dto.settings.ApprovalDTO;
import Texsmartly.Leave.Tracker.exception.ResourceNotFoundException;
import Texsmartly.Leave.Tracker.model.settings.Approval;
import Texsmartly.Leave.Tracker.model.settings.EmailTemplate;
import Texsmartly.Leave.Tracker.repository.settings.ApprovalRepository;
import Texsmartly.Leave.Tracker.repository.settings.EmailTemplateRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ApprovalsServiceImpl implements ApprovalsService{
    @Autowired
    private ApprovalRepository approvalRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EmailTemplateRepository emailTemplateRepository;

    @Override
    public List<ApprovalDTO> getApproval() {
        return approvalRepository.findAll().stream()
                .map(approval -> modelMapper.map(approval, ApprovalDTO.class))
                .toList();
    }

    @Override
    public ApprovalDTO getApprovalById(String id) {
        Optional<Approval> approval = approvalRepository.findById(id);
        return approval.map(advSal -> modelMapper.map(advSal, ApprovalDTO.class))
                .orElseThrow(() -> new ResourceNotFoundException("Advance Salary not found with id: " + id));
    }

    @Override
    public ApprovalDTO getActiveApproval() {
        return getApproval().stream()
                .filter(ApprovalDTO::isStatus)
                .findFirst()
                .orElse(null);
    }

    @Override
    public ApprovalDTO createApproval(ApprovalDTO approvalDTO) {
        if (approvalDTO.getFollowUp() != null && approvalDTO.getFollowUp().isEnabled()) {
            EmailTemplate emailTemplate = approvalDTO.getFollowUp().getEmailTemplate();
            if ("existing".equalsIgnoreCase(emailTemplate.getTemplateOption())) {
                Optional<EmailTemplate> existingTemplateOpt = emailTemplateRepository.findById(emailTemplate.getTemplateId());
                if (existingTemplateOpt.isPresent()) {
                    EmailTemplate existingTemplate = existingTemplateOpt.get();
                    emailTemplate.setFrom(existingTemplate.getFrom());
                    emailTemplate.setTo(existingTemplate.getTo());
                    emailTemplate.setCc(existingTemplate.getCc());
                    emailTemplate.setBcc(existingTemplate.getBcc());
                    emailTemplate.setReplyTo(existingTemplate.getReplyTo());
                    emailTemplate.setSubject(existingTemplate.getSubject());
                    emailTemplate.setMessage(existingTemplate.getMessage());
                } else {
                    throw new ResourceNotFoundException("Email Template not found with id: " + emailTemplate.getTemplateId());
                }
            }
        }
        Approval approval = modelMapper.map(approvalDTO, Approval.class);
        Approval savedApproval = approvalRepository.save(approval);
        return modelMapper.map(savedApproval, ApprovalDTO.class);
    }

    @Override
    public ApprovalDTO updateApprovalStatus(String id, Boolean status) {
        ApprovalDTO existingApproval = getApprovalById(id);
        existingApproval.setStatus(status);
        Approval approval1 = modelMapper.map(existingApproval, Approval.class);
        Approval savedApproval = approvalRepository.save(approval1);
        return modelMapper.map(savedApproval, ApprovalDTO.class);
    }

    @Override
    public void deleteApproval(String id) {
        approvalRepository.deleteById(id);
    }


    // Fetch approvals that have follow-ups enabled
    @Override
    public List<Approval> getApprovalsForFollowUp() {
        return approvalRepository.findByFollowUpEnabledTrue();
    }

    @Override
    public Approval updateApproval(Approval approval) {
        return approvalRepository.save(approval);
    }

    // Atomic method to update follow-up timestamp
    @Override
    public synchronized void markFollowUpSent(String approvalId, LocalDateTime sentAt) {
        Optional<Approval> optionalApproval = approvalRepository.findById(approvalId);
        if (optionalApproval.isPresent()) {
            Approval approval = optionalApproval.get();
            approval.setLastFollowUpSentAt(sentAt);
            approvalRepository.save(approval);
        }
    }
}
