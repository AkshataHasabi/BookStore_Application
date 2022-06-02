package com.example.bookstore.repository;

import com.example.bookstore.entity.CartData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<CartData,Integer> {
}
