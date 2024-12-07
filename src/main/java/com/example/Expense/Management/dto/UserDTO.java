package com.example.Expense.Management.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String userId;
    private String email;
    private String name;
    private String designation;
    private String empId;

    @NotBlank(message = "Department is required")
    private String department;
    @NotBlank(message = "Locations  is required")
    private String location;
    @NotBlank(message = "Division is required")
    private String division;

    private String role;




    public UserDTO(String email, String role, String name, String designation, String empId, String department, String division, String location) {
        this.userId = userId;
        this.email = email;
        this.name = name;
        this.designation = designation;
        this.empId = empId;
        this.department = department;
        this.location = location;
        this.division = division;
        this.role = role;
    }
}
