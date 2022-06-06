package com.example.bookstore.repository;

import com.example.bookstore.entity.OrderData;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Java persistance of API specific extension of repository
 * it provides jpa related methods such as creating & delr=eting.
 */
public interface OrderRepository extends JpaRepository<OrderData,Integer> {
}
