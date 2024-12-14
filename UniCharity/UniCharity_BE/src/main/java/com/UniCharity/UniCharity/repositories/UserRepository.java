package com.UniCharity.UniCharity.repositories;

import com.UniCharity.UniCharity.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByIdAndRole(int id, String role);
    Optional<User> findByEmail(String email);
}
