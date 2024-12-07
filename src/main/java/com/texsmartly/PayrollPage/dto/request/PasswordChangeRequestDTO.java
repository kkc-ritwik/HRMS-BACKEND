package com.texsmartly.PayrollPage.dto.request;

import lombok.Data;

@Data
public class PasswordChangeRequestDTO {
    private String currentPassword;
    private String newPassword;
}
