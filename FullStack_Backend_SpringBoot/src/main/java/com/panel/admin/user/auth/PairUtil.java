package com.panel.admin.user.auth;

import com.panel.admin.user.user.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PairUtil {
    public Role role;
    public AuthenticationResponse authenticationResponse;
}