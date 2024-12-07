package com.example.Recruitment.service;

import com.example.Recruitment.dto.OfferApprovalDTO;
import com.example.Recruitment.model.OfferApproval;
import com.example.Recruitment.repository.OfferApprovalRepository;
import com.example.Recruitment.service.OfferApprovalService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferApprovalServiceImpl implements OfferApprovalService {

    @Autowired
    private OfferApprovalRepository offerApprovalRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public OfferApprovalDTO createOfferApproval(OfferApprovalDTO offerApprovalDTO) {
        OfferApproval offerApproval = modelMapper.map(offerApprovalDTO, OfferApproval.class);
        offerApproval.setCreatedAt(new Date());
        offerApproval.setUpdatedAt(new Date());
        offerApproval.setStatus("PENDING");

        offerApproval = offerApprovalRepository.save(offerApproval);
        return modelMapper.map(offerApproval, OfferApprovalDTO.class);
    }

    @Override
    public OfferApprovalDTO updateOfferApproval(String id, OfferApprovalDTO offerApprovalDTO) {
        OfferApproval existingOffer = offerApprovalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Offer approval not found"));

        modelMapper.map(offerApprovalDTO, existingOffer);
        existingOffer.setUpdatedAt(new Date());

        existingOffer = offerApprovalRepository.save(existingOffer);
        return modelMapper.map(existingOffer, OfferApprovalDTO.class);
    }

    @Override
    public OfferApprovalDTO getOfferApproval(String id) {
        OfferApproval offerApproval = offerApprovalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Offer approval not found"));
        return modelMapper.map(offerApproval, OfferApprovalDTO.class);
    }

    @Override
    public List<OfferApprovalDTO> getAllOfferApprovals() {
        List<OfferApproval> offers = offerApprovalRepository.findAll();
        return offers.stream()
                .map(offer -> modelMapper.map(offer, OfferApprovalDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<OfferApprovalDTO> getOfferApprovalsByCandidateId(String candidateId) {
        List<OfferApproval> offers = offerApprovalRepository.findByCandidateId(candidateId);
        return offers.stream()
                .map(offer -> modelMapper.map(offer, OfferApprovalDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteOfferApproval(String id) {
        offerApprovalRepository.deleteById(id);
    }

    @Override
    public OfferApprovalDTO updateOfferStatus(String id, String status) {
        OfferApproval offer = offerApprovalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Offer approval not found"));

        offer.setStatus(status);
        offer.setUpdatedAt(new Date());

        offer = offerApprovalRepository.save(offer);
        return modelMapper.map(offer, OfferApprovalDTO.class);
    }
}