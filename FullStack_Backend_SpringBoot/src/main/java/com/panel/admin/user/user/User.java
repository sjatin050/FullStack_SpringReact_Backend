package com.panel.admin.lup.user;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Collection;
import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user")
public class User implements UserDetails {

  @Id
  @GeneratedValue
  private Integer id;

  @NotNull
  @Email(regexp = "^(?=.*[@])(?=.*[.])(?=.*@(paytm\\.com|ocltp\\.com|paytmbank\\.com|paytmpayment\\.com)$).+@.*$", message = "Invalid email address")
  private String RegisteredBy;

  @NotNull
  private String firstname;

  @NotNull
  private String lastname;

  @NotNull
  @Email(regexp = "^(?=.*[@])(?=.*[.])(?=.*@(paytm\\.com|ocltp\\.com|paytmbank\\.com|paytmpayment\\.com)$).+@.*$", message = "Invalid email address")
  private String email;

  @NotNull
  @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[*.!@#$%^&(){}\\[\\]:;<>,.?/~_+\\-=|\\\\]).{8,}$", message = "Invalid password format")
  private String password;

  @Enumerated(EnumType.STRING)
  private Role role;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(role.name()));
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
  }

  public String getRegisteredBy() {
    return RegisteredBy;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
