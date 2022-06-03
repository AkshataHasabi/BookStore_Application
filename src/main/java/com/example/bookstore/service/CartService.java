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

    //save cart details in repository method
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

    //get all cart details method , return type is list
    @Override
    public List<CartData> getAllCart() {
        log.info("ALL order records retrieved successfully");
        return cartRepository.findAll();
    }

    //get cart details by id
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

    //delete cart details by id method
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

    //update cart details by id
    @Override
    public CartData updateCartById(int id, CartDTO cartDTO) {
        Optional<CartData> cart = cartRepository.findById(id);
        Optional<BookData>  book = bookRepository.findById(cartDTO.getBookId());
        Optional<UserRegistrationData> user = userRegistrationRepository.findById(cartDTO.getUserId());
        if(cart.isPresent()) {
            if(book.isPresent() && user.isPresent()) {
                CartData cartData = new CartData(id, user.get(),book.get(), cartDTO.getQuantity());
                cartRepository.save(cartData);
                log.info("Cart record updated successfully for id "+id);
                return cartData;
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

