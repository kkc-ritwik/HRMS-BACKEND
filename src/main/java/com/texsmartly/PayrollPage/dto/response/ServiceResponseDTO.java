package com.texsmartly.PayrollPage.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceResponseDTO implements Serializable {

    private  MetaDataDTO metaDataDTO;
    private  String message;
    private  PaginationDTO pagination;
    private String data;
    private String status;
}

// ServiceResponseDTO provides a structured response that includes metadata,
// a message, data, and status information, helping clients handle the response effectively
// Serializable enables the object to be converted into a byte stream for various purposes,
// including persistence, communication, and caching.