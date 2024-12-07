package com.texsmartly.PayrollPage.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginationDTO {
    private int currentPage;
    private int totalPages;
    private long totalItems;
}