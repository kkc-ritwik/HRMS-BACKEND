package com.example.Recruitment.model.settings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "carousel")
public class CarouselStatus {
    @Id
    private String id; // MongoDB ID
    private boolean newApplication;
    private boolean telephonicRound;
    private boolean interview1;
    private boolean assessment1;
    private boolean assessment2;
    private boolean interview2;
    private boolean interview3;
    private boolean hrRound;
    private boolean backgroundVerification;
    private boolean readyToHired;
    private boolean rejected;
    private boolean hired;
    private boolean onboarded;
}
