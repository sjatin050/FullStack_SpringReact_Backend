package com.panel.admin.lup.auth;

import com.panel.admin.lup.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

  private String RegisteredBy;
  private String firstname;
  private String lastname;
  private String email;
  private String password;
  private Role role;
}
