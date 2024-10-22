package com.vendor_managemnt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vendor_managemnt.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

   Optional<User> findByEmail(String email);

}
