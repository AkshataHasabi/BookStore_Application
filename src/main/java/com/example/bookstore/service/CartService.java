package com.example.bookstore.service;

import com.example.bookstore.dto.CartDTO;
import com.example.bookstore.entity.BookData;
import com.example.bookstore.entity.CartData;
import com.example.bookstore.entity.UserRegistrationData;
import com.example.bookstore.exception.BookStoreException;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.CartRepository;
import com.example.bookstore.repository.UserRegistrationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CartService implements ICartService{
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRegistrationRepository userRegistrationRepository;
    @Override
    public CartData insert(CartDTO cartDTO) {
        Optional<BookData> bookData = bookRepository.findById(cartDTO.getBookId());
        Optional<UserRegistrationData> userRegistrationData = userRegistrationRepository.findById(cartDTO.getUserId());
        if (bookData.isPresent() && userRegistrationData.isPresent()) {
            CartData cartData = new CartData(userRegistrationData.get(), bookData.get(), cartDTO.getQuantity());
            cartRepository.save(cartData);
            return cartData;
        } else {
            throw new BookStoreException("Bookdata or userregistrationdatac not found");
        }
    }

    @Override
    public List<CartData> getAllCart() {
       return cartRepository.findAll();
    }

    @Override
    public CartData  getCartById(int id) {
        Optional<CartData> cartData=cartRepository.findById(id);
        if (cartData.isPresent()){
            return cartData.get();
        }
        else {
            throw new BookStoreException(" Didn't find any record for this particular cartId");
        }
    }

    @Override
    public void deleteCartData(int id) {
        Optional<CartData> deleteData=cartRepository.findById(id);
        if (deleteData.isPresent()){
            cartRepository.deleteById(id);
        }
        else {
            throw new BookStoreException(" Did not get any cart for specific cart id ");
        }
    }

    @Override
    public CartData updateCartById(int id, CartDTO cartDTO) {
        Optional<CartData> cart = cartRepository.findById(id);
        Optional<BookData>  book = bookRepository.findById(cartDTO.getBookId());
        Optional<UserRegistrationData> user = userRegistrationRepository.findById(cartDTO.getUserId());
        if(cart.isPresent()) {
            if(book.isPresent() && user.isPresent()) {
                CartData newCart = new CartData(id, user.get(),book.get(), cartDTO.getQuantity());
                cartRepository.save(newCart);
                log.info("Cart record updated successfully for id "+id);
                return newCart;
            }
            else {
                throw new BookStoreException("Book or User doesn't exists");
            }
        }
        else {
            throw new BookStoreException("Cart Record doesn't exists");
        }
    }

}

