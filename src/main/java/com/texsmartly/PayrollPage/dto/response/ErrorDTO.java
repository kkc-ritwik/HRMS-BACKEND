package com.texsmartly.PayrollPage.dto.response;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDTO {
    private String field;
    @NotNull(message = "Raise an incident ticket")
    private String message;
}