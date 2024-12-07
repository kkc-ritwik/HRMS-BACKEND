package com.texsmartly.PayrollPage.service.impl;


import com.texsmartly.PayrollPage.model.payroll.ClaimNumberCounter;
import com.texsmartly.PayrollPage.repository.ClaimCounterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class ClaimNumberGeneratorService {
    @Autowired
    private ClaimCounterRepository claimCounterRepository;

    @Transactional
    public synchronized String generateClaimNumber() {
        ClaimNumberCounter counter = claimCounterRepository.findById("claimNumber")
                .orElseGet(() -> {
                    // Initialize counter if it doesn't exist
                    ClaimNumberCounter newCounter = new ClaimNumberCounter();
                    newCounter.setId("claimNumber");
                    newCounter.setSequence(1L);
                    return claimCounterRepository.save(newCounter);
                });

        // Get the current sequence number
        Long claimNumber = counter.getSequence();

        // Increment and save the updated sequence
        counter.setSequence(claimNumber + 1);
        claimCounterRepository.save(counter);

        // Convert claim number to a string with leading zeros, if required
        return String.format("CLAIM-%06d", claimNumber); // e.g., CLAIM-000001
    }
}
