/*
    Author  : Ruvi 
    Contact : ruvi.contact@gmail.com
*/
package lk.sample.securitysample.service.impl;

import lk.sample.securitysample.dto.SignUpRequest;
import lk.sample.securitysample.entity.User;
import lk.sample.securitysample.repo.UserRepo;
import lk.sample.securitysample.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Override
    public UserDetailsService userDetailService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return userRepo.findByUsername(username)
                        .orElseThrow(()-> new UsernameNotFoundException("User Not Found !"));
            }
        };
    }


}
