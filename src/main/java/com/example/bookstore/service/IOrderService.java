package com.example.bookstore.service;

import com.example.bookstore.dto.CartDTO;
import com.example.bookstore.dto.OrderDTO;
import com.example.bookstore.entity.CartData;
import com.example.bookstore.entity.OrderData;

import java.util.List;

public interface IOrderService {
     OrderData insert(OrderDTO orderDTO);
     List<OrderData> getAllOrder();
     OrderData getOrderById(int id);
     OrderData updateOrderById(int id,OrderDTO orderDTO);
     OrderData deleteOrderData(int id);
}
