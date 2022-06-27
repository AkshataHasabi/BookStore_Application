package com.example.bookstore.repository;

import com.example.bookstore.entity.UserRegistrationData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * Java persistance of API specific extension of repository
 * it provides jpa related methods such as creating & deleting.
 */
public interface UserRegistrationRepository extends JpaRepository<UserRegistrationData,Integer> {
    List<UserRegistrationData> findByEmail(String email);

    Optional<UserRegistrationData> findByEmailAndPassword(String email, String password);

    @Query(value = "select * from user_data where email= :email", nativeQuery = true)
    Optional<UserRegistrationData> findByEmailId(String email);
}
