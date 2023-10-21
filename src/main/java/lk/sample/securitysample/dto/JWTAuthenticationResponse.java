/*
    Author  : Ruvi 
    Contact : ruvi.contact@gmail.com
*/
package lk.sample.securitysample.dto;

import lombok.Data;

@Data
public class JWTAuthenticationResponse {

    private String token;

    private String refreshToken;
}
