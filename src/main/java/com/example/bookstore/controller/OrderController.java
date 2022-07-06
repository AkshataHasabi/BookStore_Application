package com.example.bookstore.controller;

import com.example.bookstore.dto.OrderDTO;
import com.example.bookstore.dto.ResponseDTO;
import com.example.bookstore.entity.OrderData;
import com.example.bookstore.service.IOrderService;
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
@CrossOrigin
@RestController
@RequestMapping("order")
/**
 * create a class name as OrderController
 */
public class OrderController {
    /**
     * 3) @AutoMapping :-
     *          @Autowiring feature of spring framework enables you to inject the object dependency implicitly.
     *          It internally uses setter or constructor injection.
     *
     * - Autowired IOrderService interface so we can inject its dependency here
     */
    @Autowired
    private IOrderService iOrderService;
    /**
     * Inserting the data to the order repository
     * @valid this will tell spring to go & validate the data passed into the controller.
     * @RequestBody:- it is used to bind http request body with a dto object in method parameter
     * @param orderdto - data of the order
     * @return - data of the order
     */
    //create order data
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> insertOrder(@Valid @RequestBody OrderDTO orderdto){
        OrderData orderData = iOrderService.insert(orderdto);
        ResponseDTO responseDTO = new ResponseDTO("Order placed successfully !",orderData);
        return new ResponseEntity(responseDTO, HttpStatus.CREATED);
    }
    /**
     * Ability to cal api to retrieve all order
     * @param  - represent  id token
     *Ability to retrieve all data from the order repository
     *@return -list of orders
     */
    //get all order details
    @GetMapping("/getallorders")
    public ResponseEntity<ResponseDTO> getAllOrder(){
        List<OrderData> orderData = iOrderService.getAllOrder();
        ResponseDTO responseDTO = new ResponseDTO("All records retrieved successfully !",orderData);
        return new ResponseEntity(responseDTO,HttpStatus.OK);
    }
    /**
     * Ability to cal api to retrieve order record by id
     * @param token - represent  id
     * @return - order of particular id entered by user
     */
    //get order details by id
    @GetMapping("get/{token}")
    public ResponseEntity<ResponseDTO> getOrderById(@PathVariable String token){
        OrderData orderData = iOrderService.getOrderById(token);
        ResponseDTO responseDTO = new ResponseDTO("get call successfully !",orderData);
        return new ResponseEntity(responseDTO,HttpStatus.OK);
    }
    /**
     * Ability to cancel order of particular id and user id
     * @param token - order id
     * @param userId - user id
     * @return - cancel order by particular id, and user id
     */
    //cancel order by orderid and userid
    @PutMapping("/cancelorder/{token}/{userId}")
    public ResponseEntity<ResponseDTO> cancelOrderById(@PathVariable String token,@PathVariable int userId){
        OrderData orderData = iOrderService.cancelOrderById(token,userId);
        ResponseDTO responseDTO = new ResponseDTO("order record canceled successfully !",orderData);
        return new ResponseEntity(responseDTO,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteOrderRecord(@PathVariable Integer id){
        OrderData newOrder = iOrderService.deleteOrderRecord(id);
        ResponseDTO dto = new ResponseDTO("Record deleted successfully !",newOrder);
        return new ResponseEntity(dto,HttpStatus.ACCEPTED);
    }

    @GetMapping("/totalPrice")
    public ResponseEntity<ResponseDTO> getTotalPrice(){
        Integer newOrder = iOrderService.totalPrice();
        ResponseDTO dto = new ResponseDTO("Total Price calculated successfully !",newOrder);
        return new ResponseEntity(dto,HttpStatus.ACCEPTED);
    }
}
