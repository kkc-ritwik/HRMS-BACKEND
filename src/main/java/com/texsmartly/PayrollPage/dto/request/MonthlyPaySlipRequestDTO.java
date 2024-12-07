package com.texsmartly.PayrollPage.dto.request;


import com.texsmartly.PayrollPage.dto.PersonalDetailsDTO;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonthlyPaySlipRequestDTO {

    @Id
    String id = UUID.randomUUID().toString();
    @NotNull(message = "Employee Id should not be null")
    private PersonalDetailsDTO personalDetailsDTO;
    @NotNull(message = "from Date should not be null")
    private LocalDate fromDate;
    @NotNull(message = "to Date Id should not be null")
    private  LocalDate toDate;
}
