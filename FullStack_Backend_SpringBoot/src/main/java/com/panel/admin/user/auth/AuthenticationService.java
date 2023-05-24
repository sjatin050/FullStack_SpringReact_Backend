package com.panel.admin.lup.auth;

import com.panel.admin.lup.config.JwtService;
import com.panel.admin.lup.user.User;
import com.panel.admin.lup.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserRepository repository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager; // created a Bean of AuthenticationManager

  /***
   *
   * @param request  the parameters of the new user
   * @return pair of { boolean : is the user already registered , AuthenticationResponse }
   */
  public PairUtil1 register(RegisterRequest request) {
    boolean user = repository.findByEmail(request.getEmail()).isEmpty();
    var user1 = User.builder()
            .RegisteredBy(request.getRegisteredBy())
            .firstname(request.getFirstname())
            .lastname(request.getLastname())
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(request.getRole())
            .build();
    boolean alreadyRegistered = true;

    if(user)
    {
      alreadyRegistered=false;
      repository.save(user1);
    }

    var jwtToken = jwtService.generateToken(user1);
    var response = AuthenticationResponse.builder()
            .accessToken(jwtToken)
            .build();

    return new PairUtil1(alreadyRegistered,response);
  }

  /**
   *
   * @param request  the parameters of the user who wants to log in
   * @return the pair of { role : ADMIN / USER , AuthenticationResponse }
   */
  public PairUtil authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );


    var user = repository.findByEmail(request.getEmail())
        .orElseThrow();

    var userRole = user.getRole();
    var jwtToken = jwtService.generateToken(user);

    var response = AuthenticationResponse.builder()
            .accessToken(jwtToken)
            .build();

    return new PairUtil(userRole,response);

  }

    public AuthenticationResponse changePassword(ChangePasswordRequest request) {

        Optional<User> user = repository.findByEmail(request.getEmail());
      //System.out.println("\n\n\n");
        //System.out.println(user.get().getEmail());
        repository.deleteById(user.get().getId());
      //System.out.println("\n\n\n");

      var user1 = User.builder()
              .RegisteredBy(user.get().getRegisteredBy())
              .firstname(user.get().getFirstname())
              .lastname(user.get().getLastname())
              .email(user.get().getEmail())
              .password(passwordEncoder.encode(request.getNewPassword()))
              .role(user.get().getRole())
              .build();

      repository.save(user1);

      var jwtToken = jwtService.generateToken(user1);

      return AuthenticationResponse.builder()
              .accessToken(jwtToken)
              .build();

    }
}
