package com.texsmartly.PayrollPage.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDTO {
    @Id
    String id = UUID.randomUUID().toString();

    @NotEmpty (message = "Username mandatory")
    @NotNull(message = "Username not null")
    private String username;

    @NotEmpty (message = "password mandatory")
    @NotNull(message = "password not blank")
    private String password;

}
