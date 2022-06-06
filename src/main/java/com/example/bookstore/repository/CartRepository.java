package com.example.bookstore.repository;

import com.example.bookstore.entity.CartData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Java persistance of API specific extension of repository
 * it provides jpa related methods such as creating & delr=eting.
 */
@Repository
public interface CartRepository extends JpaRepository<CartData,Integer> {
}
