package com.example.bookstore.service;

import com.example.bookstore.dto.CartDTO;
import com.example.bookstore.entity.BookData;
import com.example.bookstore.entity.CartData;
import com.example.bookstore.entity.UserRegistrationData;
import com.example.bookstore.exception.BookStoreException;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.CartRepository;
import com.example.bookstore.repository.UserRegistrationRepository;
import com.example.bookstore.util.EmailSenderService;
import com.example.bookstore.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
/**
 * @Service:-it is the annotation is used on the class level,
 * it annotates class to perform service tasks like executing bussiness logic & performing calculation.
 */
@Service
@Slf4j
/**
 * Created CartService class to serve api calls done by controller layer
 */
public class CartService implements ICartService{
    /**
     * Autowired CartRepository interface to inject its dependency here
     */
    @Autowired
    private CartRepository cartRepository;
    /**
     * Autowired BookRepository interface to inject its dependency here
     */
    @Autowired
    private BookRepository bookRepository;
    /**
     * Autowired  UserRegistrationRepository interface to inject its dependency here
     */
    @Autowired
    private UserRegistrationRepository userRegistrationRepository;
    /**
     * Autowired util interface to inject its dependency here
     */
    @Autowired
    private TokenUtil util;

    /**
     * create a method name as insert
     * Ability to save cart details to repository
     * @param cartDTO - cart data
     * @return - save all data
     */
    //save cart details in repository method
    @Override
    public CartData insert(CartDTO cartDTO) {
        Optional<BookData> bookData = bookRepository.findById(cartDTO.getBookId());
        Optional<UserRegistrationData> userRegistrationData = userRegistrationRepository.findById(cartDTO.getUserId());
        if (bookData.isPresent() && userRegistrationData.isPresent()) {
            CartData cartData = new CartData(userRegistrationData.get(), bookData.get(), cartDTO.getQuantity());
            return cartRepository.save(cartData);

        } else {
            throw new BookStoreException("Bookdata or userregistrationdata not found");
        }
    }
    /**
     * create a method name as getAllCart
     * Ability to get all cart' data by findAll() method
     * @return - all cart data
     */
    //get all cart details method , return type is list
    @Override
    public List<CartData> getAllCart() {
            List<CartData> listOfCartdata = cartRepository.findAll();
            log.info("ALL cart records retrieved successfully");
            return listOfCartdata;
    }
    /**
     * create a method name as getCartById
     * - Ability to get cart data by cartId
     * @param id - cart id
     * @return - cart data by id
     */
    //get cart details by id
    @Override
    public CartData getCartById(int id) {

        Optional<CartData> cartData=cartRepository.findById(id);
        if (cartData.isPresent()){
            return cartData.get();
        }
        else {
            throw new BookStoreException(" Didn't find any record for this particular cartId");
        }
    }

//    @Override
//    public CartData getBookById(int bookId) {
//        Optional<CartData> cartData=cartRepository.findById(bookId);
//        if (cartData.isPresent()){
//            return cartData.get();
//        }
//        else {
//            throw new BookStoreException(" Didn't find any record for this particular cartId");
//        }
//    }

    public CartData getCartRecordByBookId(Integer bookId) {
        Optional<CartData> cart = cartRepository.findByBookId(bookId);
        if(cart.isEmpty()) {
            return null;
            //throw new BookStoreException("Cart Record doesn't exists");
        }
        else {
            log.info("Cart record retrieved successfully for book id "+bookId);
            return cart.get();
        }
    }

    @Override
    public List<CartData> getCartRecordByUserId(Integer userId) {
        List<CartData> cart = cartRepository.findByUserId(userId);
//				if(cart.isEmpty()) {
//					//return null;
//					//throw new BookStoreException("Cart Record doesn't exists");
//				}
//				else {
            log.info("Cart record retrieved successfully for book id "+userId);
            return cart;
            //}
        }


    /**
     * create a method name as updateCartById
     * Ability to update cart data for particular id
     * @param id - cart id
     * @param cartDTO - cart data
     * @return - updated cart information in JSON format
     */
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
    /**
     * create a method name as deleteCartData
     * ability to delete data by particular cart id
     * @param id - cart id
     * @return - if id is not found that time it will send exception message
     */
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

    @Override
    public CartData updateCartByIds(Integer id, Integer quantity) {
        Optional<CartData> cart = cartRepository.findById(id);
        Optional<BookData>  book =bookRepository.findById(cart.get().getBookData().getBookId());
        if(cart.isPresent()) {
            if(quantity < book.get().getQuantity()) {
                cart.get().setQuantity(quantity);
                cartRepository.save(cart.get());
                book.get().setQuantity(book.get().getQuantity() - (quantity - cart.get().getQuantity()));
                bookRepository.save(book.get());
                return cart.get();
            }
            else {
                throw new BookStoreException("Requested quantity is not available");
            }

        }
        else {
            throw new BookStoreException("Cart Record doesn't exists");
        }
    }
    }




