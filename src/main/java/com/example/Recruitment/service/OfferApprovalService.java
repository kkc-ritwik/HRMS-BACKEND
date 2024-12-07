package com.example.Recruitment.service;

import com.example.Recruitment.dto.OfferApprovalDTO;
import java.util.List;

public interface OfferApprovalService {
    OfferApprovalDTO createOfferApproval(OfferApprovalDTO offerApprovalDTO);
    OfferApprovalDTO updateOfferApproval(String id, OfferApprovalDTO offerApprovalDTO);
    OfferApprovalDTO getOfferApproval(String id);
    List<OfferApprovalDTO> getAllOfferApprovals();
    List<OfferApprovalDTO> getOfferApprovalsByCandidateId(String candidateId);
    void deleteOfferApproval(String id);
    OfferApprovalDTO updateOfferStatus(String id, String status);
}