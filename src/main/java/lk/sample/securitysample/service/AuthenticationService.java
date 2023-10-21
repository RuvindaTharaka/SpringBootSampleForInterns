/*
    Author  : Ruvi 
    Contact : ruvi.contact@gmail.com
*/
package lk.sample.securitysample.service;

import lk.sample.securitysample.dto.JWTAuthenticationResponse;
import lk.sample.securitysample.dto.RefreshTokenRequest;
import lk.sample.securitysample.dto.SignInRequest;
import lk.sample.securitysample.dto.SignUpRequest;
import lk.sample.securitysample.entity.User;

public interface AuthenticationService {

    User signUp (SignUpRequest signUpRequest);

    JWTAuthenticationResponse signIn (SignInRequest signInRequest);

    JWTAuthenticationResponse refreshToken (RefreshTokenRequest refreshTokenRequest);
}
