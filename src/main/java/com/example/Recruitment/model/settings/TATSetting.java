package com.example.Recruitment.model.settings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "tat-setting")
public class TATSetting {
    @Id
    private String id;
    private boolean isDaysAllottedForApprover;
    private int daysAllottedForApprover;

    private boolean isRemindApprover;
    private int reminderDaysBefore;

    private ApproverAction intermediateApproverAction;

    private ApproverAction finalApproverAction;

    private boolean remindFinalApprover;
    private int remindFinalApproverDays;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ApproverAction {
        private boolean moveToNextApprover;
        private boolean autoReject;
        private String assignTo;
    }
}
