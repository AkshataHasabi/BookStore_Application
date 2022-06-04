package com.example.bookstore.service;

import com.example.bookstore.dto.BookDTO;
import com.example.bookstore.dto.CartDTO;
import com.example.bookstore.dto.ResponseDTO;
import com.example.bookstore.entity.BookData;
import com.example.bookstore.entity.CartData;

import java.util.List;

public interface ICartService {
    String insert(CartDTO cartDTO);
    List<CartData> getAllCart(String token);
    CartData getCartById(String token);
    CartData updateCartById(String token,CartDTO cartDTO);
    void deleteCartData(String token);
}
