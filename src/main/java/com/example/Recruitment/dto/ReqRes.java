package com.example.Recruitment.dto;

import com.example.Recruitment.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReqRes {
    private int statusCode;
    private String error;
    private String message;
    private String token;
    private String refreshToken;
    private String expirationTime;

    @NotBlank(message = "Name is required", groups = ValidationGroups.Registration.class)
    @Size(max = 50, message = "Name should not exceed 50 characters", groups = ValidationGroups.Registration.class)
    private String name;

    @Size(max = 100, message = "City should not exceed 100 characters", groups = ValidationGroups.Registration.class)
    private String city;

    @NotBlank(message = "Role is required", groups = ValidationGroups.Registration.class)
    private String role;

    @NotBlank(message = "Email is required", groups = {ValidationGroups.Registration.class, ValidationGroups.Login.class})
    @Email(message = "Invalid email format", groups = {ValidationGroups.Registration.class, ValidationGroups.Login.class})
    private String email;

    @NotBlank(message = "Password is required", groups = {ValidationGroups.Registration.class, ValidationGroups.Login.class})
    @Size(min = 8, message = "Password must be at least 8 characters long", groups = ValidationGroups.Registration.class)
    private String password;

    private User user;
    private List<User> userList;
}