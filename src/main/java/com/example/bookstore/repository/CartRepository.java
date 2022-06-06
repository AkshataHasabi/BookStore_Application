package com.example.bookstore.repository;

import com.example.bookstore.entity.CartData;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Java persistance of API specific extension of repository
 * it provides jpa related methods such as creating & delr=eting.
 */
public interface CartRepository extends JpaRepository<CartData,Integer> {
}
