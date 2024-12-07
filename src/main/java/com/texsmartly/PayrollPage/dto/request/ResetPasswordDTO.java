package com.texsmartly.PayrollPage.dto.request;

import lombok.Data;

@Data
public class ResetPasswordDTO {

  private String userEmail;
  private String token;
  private String newPassword;
}
