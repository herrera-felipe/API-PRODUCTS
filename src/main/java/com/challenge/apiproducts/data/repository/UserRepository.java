package com.challenge.apiproducts.data.repository;

import com.challenge.apiproducts.data.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUserName(String username);
    UserEntity findByEmail(String email);
    boolean existsByEmail(String email);
}
