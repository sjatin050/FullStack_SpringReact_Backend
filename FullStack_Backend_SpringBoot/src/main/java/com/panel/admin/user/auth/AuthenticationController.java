package com.panel.admin.lup.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;

  /***
   *
   * @param request  the parameters of the new user
   * @return The HTTP response code with pair of { boolean : is the user already registered , AuthenticationResponse }
   */
  @PostMapping("/register")
  public ResponseEntity<PairUtil1> register(
      @RequestBody RegisterRequest request
  ) {
    return ResponseEntity.ok(service.register(request));
  }

  /***
   *
   * @param request  the parameters of the user who wants to log in
   * @return The HTTP response code with pair of { role : ADMIN / USER , AuthenticationResponse }
   */
  @PostMapping("/authenticate")
  public ResponseEntity<PairUtil> authenticate(
      @RequestBody AuthenticationRequest request
  ) {
    return ResponseEntity.ok(service.authenticate(request));
  }

  @PostMapping("/changePassword")
  public ResponseEntity<AuthenticationResponse> changePassword(@RequestBody ChangePasswordRequest request){
    return ResponseEntity.ok(service.changePassword(request));
  }

}
