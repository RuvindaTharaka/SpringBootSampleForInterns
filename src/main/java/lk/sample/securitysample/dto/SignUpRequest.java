/*
    Author  : Ruvi 
    Contact : ruvi.contact@gmail.com
*/
package lk.sample.securitysample.dto;

import lombok.Data;

@Data
public class SignUpRequest {


    private String username;

    private String email;

    private String password;

    private String Role;
}
