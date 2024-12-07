package com.example.Recruitment.model.settings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "general-setting")
public class GeneralSetting {
    @Id
    private String id;
    private boolean allowView;
    private boolean notifyIntermediate;
    private boolean notifyFinal;
    private boolean skipApprover;
    private boolean commentApprove;
    private boolean commentReject;
    private String approverOption;
    private boolean allowRequestCancellation;
}