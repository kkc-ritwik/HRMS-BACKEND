package com.example.Recruitment.dto.settings;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class GeneralSettingDTO {
    private String id;

    @NotNull(message = "Allow view field cannot be null")
    private Boolean allowView;

    @NotNull(message = "Notify intermediate field cannot be null")
    private Boolean notifyIntermediate;

    @NotNull(message = "Notify final field cannot be null")
    private Boolean notifyFinal;

    @NotNull(message = "Skip approver field cannot be null")
    private Boolean skipApprover;

    @NotNull(message = "Comment approve field cannot be null")
    private Boolean commentApprove;

    @NotNull(message = "Comment reject field cannot be null")
    private Boolean commentReject;

    @NotBlank(message = "Approver option cannot be blank")
    private String approverOption;

    @NotNull(message = "Allow request cancellation field cannot be null")
    private Boolean allowRequestCancellation;
}
