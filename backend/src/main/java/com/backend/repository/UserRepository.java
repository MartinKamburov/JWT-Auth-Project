package com.backend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    Optional<User> findByVerificationCode(String verificationCode);
}