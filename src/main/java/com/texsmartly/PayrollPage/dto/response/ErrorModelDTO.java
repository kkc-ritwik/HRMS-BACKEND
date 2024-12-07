package com.texsmartly.PayrollPage.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorModelDTO implements Serializable {
    private String fieldName;
    private String message;
    private String description;

}
