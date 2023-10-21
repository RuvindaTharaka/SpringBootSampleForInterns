/*
    Author  : Ruvi 
    Contact : ruvi.contact@gmail.com
*/
package lk.sample.securitysample.repo;

import lk.sample.securitysample.entity.User;
import lk.sample.securitysample.util.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {

    Optional<User> findByUsername(String username);

    User findByRole(Role role);
}
