package com.FinTrack.repository;

import com.FinTrack.model.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsersRepository extends JpaRepository<Users, String> {

    UserDetails findByEmail(String email);


}
