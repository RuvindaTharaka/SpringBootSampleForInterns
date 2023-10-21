/*
    Author  : Ruvi 
    Contact : ruvi.contact@gmail.com
*/
package lk.sample.securitysample.service.impl;

import lk.sample.securitysample.dto.JWTAuthenticationResponse;
import lk.sample.securitysample.dto.RefreshTokenRequest;
import lk.sample.securitysample.dto.SignInRequest;
import lk.sample.securitysample.dto.SignUpRequest;
import lk.sample.securitysample.entity.User;
import lk.sample.securitysample.repo.UserRepo;
import lk.sample.securitysample.service.AuthenticationService;
import lk.sample.securitysample.service.JWTService;
import lk.sample.securitysample.util.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JWTService jwtService;

    public User signUp (SignUpRequest signUpRequest) {
        User user = new User ();
        /*If you need more roles please add them in Role enum and manage here*/
        if(signUpRequest.getRole().equals("ADMIN")){
            user.setUsername(signUpRequest.getUsername());
            user.setEmail(signUpRequest.getEmail());
            user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
            user.setRole(Role.ADMIN);
        }else if (signUpRequest.getRole().equals("USER")){
            user.setUsername(signUpRequest.getUsername());
            user.setEmail(signUpRequest.getEmail());
            user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
            user.setRole(Role.USER);
        }else {
            user.setUsername(signUpRequest.getUsername());
            user.setEmail(signUpRequest.getEmail());
            user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
            user.setRole(Role.USER);
        }


        return userRepo.save(user);
    }

    public JWTAuthenticationResponse signIn (SignInRequest signInRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getUsername(),signInRequest.getPassword()));
        var user = userRepo.findByUsername(signInRequest.getUsername()).orElseThrow(()-> new IllegalArgumentException("Invalid Username or Password!"));
        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(),user);

        JWTAuthenticationResponse jwtAuthenticationResponse = new JWTAuthenticationResponse();

        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);
        return jwtAuthenticationResponse;
    }

    public JWTAuthenticationResponse refreshToken (RefreshTokenRequest refreshTokenRequest) {
        String username = jwtService.extractUsername(refreshTokenRequest.getToken());
        User user = userRepo.findByUsername(username).orElseThrow();
        if (jwtService.isTokenValid(refreshTokenRequest.getToken(),user)) {
            var jwt = jwtService.generateToken(user);

            JWTAuthenticationResponse jwtAuthenticationResponse = new JWTAuthenticationResponse();

            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());
            return jwtAuthenticationResponse;
        }

        return null;
    }
}
