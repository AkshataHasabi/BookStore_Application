package com.example.bookstore.repository;

import com.example.bookstore.entity.OrderData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderData,Integer> {
}
