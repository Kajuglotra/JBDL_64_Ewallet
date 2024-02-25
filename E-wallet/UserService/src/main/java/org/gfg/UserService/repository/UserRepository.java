package org.gfg.UserService.repository;

import org.gfg.UserService.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByPhoneNo(String phoneNo);

}
