package com.example.bookstore.service;

import com.example.bookstore.dto.BookDTO;
import com.example.bookstore.dto.CartDTO;
import com.example.bookstore.dto.ResponseDTO;
import com.example.bookstore.entity.BookData;
import com.example.bookstore.entity.CartData;

import java.util.List;

public interface ICartService {
    CartData insert(CartDTO cartDTO);
    List<CartData> getAllCart();
    CartData getCartById(int id);
    CartData updateCartById(int id,CartDTO cartDTO);
    void deleteCartData(int id);
    CartData updateCartByIds(Integer id, Integer quantity);
    CartData getCartRecordByBookId(Integer bookId);

    List<CartData> getCartRecordByUserId(Integer userId);
}
