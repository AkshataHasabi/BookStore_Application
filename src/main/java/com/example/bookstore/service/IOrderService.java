package com.example.bookstore.service;

import com.example.bookstore.dto.CartDTO;
import com.example.bookstore.dto.OrderDTO;
import com.example.bookstore.entity.CartData;
import com.example.bookstore.entity.OrderData;

import java.util.List;

public interface IOrderService {
     String insert(OrderDTO orderDTO);
     List<OrderData> getAllOrder(String token);
     OrderData getOrderById(String token);
     OrderData cancelOrderById(String token, int userId);
     }
