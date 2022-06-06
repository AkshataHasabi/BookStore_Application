package com.example.bookstore.controller;


import com.example.bookstore.dto.CartDTO;
import com.example.bookstore.dto.ResponseDTO;
import com.example.bookstore.entity.CartData;
import com.example.bookstore.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
/**
 *  1) @RestController :-
 *           @RestController is used for making restful web services with the help of the @RestController annotation.
 *           This annotation is used at the class level and allows the class to handle the requests made by the client
 * 2) @RequestMapping :-
 *           @RequestMapping used to map web requests onto specific handler classes and/or handler methods.
 *           RequestMapping can be applied to the controller class as well as methods
 *
 * - Created controller so that we can perform rest api calls
 */
@RestController
@RequestMapping("cart")
/**
 * create class name as CartController
 */
public class CartController {
    /**
     * @AutoMapping :-
     *          @Autowiring feature of spring framework enables you to inject the object dependency implicitly.
     *          It internally uses setter or constructor injection.
     *
     * - Autowired  ICartService interface, so we can inject its dependency here
     */
    @Autowired
    private ICartService iCartService;
    /**
     * - Ability to get all cart' data by findAll() method
     * @param token - put cart id in url path
     * @return :- showing all data
     */
    //get all cart details
    @GetMapping("/getall/{token}")
    public ResponseEntity<ResponseDTO> getAllCart(@PathVariable String token){
        List<CartData> cartData=iCartService.getAllCart(token);
        ResponseDTO responseDTO=new ResponseDTO("Get call Success",cartData);
        return  new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
    /**
     * Ability to get cart details by id
     * @param token - put cart id in url path
     * @return cart details by cart id
     */
    //get cart details by id
    @GetMapping("give/{token}")
    public ResponseEntity<ResponseDTO> getCartById(@PathVariable String token){
        CartData cartData=iCartService.getCartById(token);
        ResponseDTO responseDTO=new ResponseDTO("Get call Success for id successfull",cartData);
        return  new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
    /**
     * - Ability to save cart details to repository
     * @apiNote- accepts the cart data in JSON format and stores it in DB
     * @RequestBody:- it is used to bind http request body with a dto object in method parameter
     * @valid this will tell spring to go & validate the data passed into the controller.
     * @param cartDTO - cart data
     * @return - save cart data
     */
    //create cart details
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> addCart(@Valid @RequestBody CartDTO cartDTO){
        String cartData =iCartService.insert(cartDTO);
        ResponseDTO responseDTO=new ResponseDTO("created cart data succesfully",cartData);
        return  new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
    /**
     * update cart data by cart id
     * @param token - put cart id in url path
     * @valid this will tell spring to go & validate the data passed into the controller.
     * * @RequestBody:- it maps the body of the http request to an object
     * @param cartDTO - all data
     * @return - update data
     */
    //update cart details by id
    @PutMapping("/update/{token}")
    public ResponseEntity<ResponseDTO> updateBooksById(@PathVariable String token,@Valid @RequestBody CartDTO cartDTO){
        CartData cartData=iCartService.updateCartById(token,cartDTO);
        ResponseDTO responseDTO=new ResponseDTO("updated cart data succesfully",cartData);
        return  new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
    /**
     * ability to delete cart data by cart id
     * @param token - put cart id in url path
     * @return - delete data
     */
    //delete cart details by id
    @DeleteMapping("/cartdelete/{token}")
    public ResponseEntity<ResponseDTO> deleteCartData(@PathVariable String token){
        iCartService.deleteCartData(token);
        ResponseDTO responseDTO=new ResponseDTO("deleted succesfully",token);
        return  new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
}
