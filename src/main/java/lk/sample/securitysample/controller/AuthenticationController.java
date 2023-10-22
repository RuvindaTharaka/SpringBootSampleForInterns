/*
    Author  : Ruvi 
    Contact : ruvi.contact@gmail.com
*/
package lk.sample.securitysample.controller;

import lk.sample.securitysample.dto.JWTAuthenticationResponse;
import lk.sample.securitysample.dto.RefreshTokenRequest;
import lk.sample.securitysample.dto.SignInRequest;
import lk.sample.securitysample.dto.SignUpRequest;
import lk.sample.securitysample.entity.User;
import lk.sample.securitysample.repo.UserRepo;
import lk.sample.securitysample.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping ("/api/v1/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;


    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody SignUpRequest signUpRequest) {
        String username = signUpRequest.getUsername();
        if (authenticationService.checkUserExists(username)) {
            return ResponseEntity.badRequest().body("User Already Exists!");
        }
        boolean signupOK = null != authenticationService.signUp(signUpRequest);
        return ResponseEntity.ok(signupOK ? "User Successfully Signed Up!" : "Sign up Unsuccessful!");
    }

    @PostMapping("/signin")
    public ResponseEntity<JWTAuthenticationResponse> signIn (@RequestBody SignInRequest signInRequest) {
        return ResponseEntity.ok(authenticationService.signIn(signInRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JWTAuthenticationResponse> refreshToken (@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }
}
