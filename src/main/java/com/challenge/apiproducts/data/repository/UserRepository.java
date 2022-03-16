package com.challenge.apiproducts.data.repository;

import com.challenge.apiproducts.data.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUsername(String username);
    Optional<UserEntity> findByEmail(String email);
}
