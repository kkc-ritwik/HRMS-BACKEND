package com.texsmartly.PayrollPage.dto.response;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordChangeResponseDTO {
    private boolean result;
    @NotNull(message = "Your password has been changed")
    private String message;

}
