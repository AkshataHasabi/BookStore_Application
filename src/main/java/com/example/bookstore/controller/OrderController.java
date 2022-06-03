package com.example.bookstore.controller;

import com.example.bookstore.dto.OrderDTO;
import com.example.bookstore.dto.ResponseDTO;
import com.example.bookstore.entity.OrderData;
import com.example.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    //create order data
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> insertOrder(@Valid @RequestBody OrderDTO orderdto){
        String orderData = orderService.insert(orderdto);
        ResponseDTO responseDTO = new ResponseDTO("Order placed successfully !",orderData);
        return new ResponseEntity(responseDTO, HttpStatus.CREATED);
    }

    //get all order details
    @GetMapping("/getallorders/{token}")
    public ResponseEntity<ResponseDTO> getAllOrder(@PathVariable String token){
        List<OrderData> orderData = orderService.getAllOrder(token);
        ResponseDTO responseDTO = new ResponseDTO("All records retrieved successfully !",orderData);
        return new ResponseEntity(responseDTO,HttpStatus.OK);
    }

    //get order details by id
    @GetMapping("get/{token}")
    public ResponseEntity<ResponseDTO> getOrderById(@PathVariable String token){
        OrderData orderData = orderService.getOrderById(token);
        ResponseDTO responseDTO = new ResponseDTO("get call successfully !",orderData);
        return new ResponseEntity(responseDTO,HttpStatus.OK);
    }

    //cancel order by orderid and userid
    @PutMapping("/cancelorder/{token}/{userId}")
    public ResponseEntity<ResponseDTO> cancelOrderById(@PathVariable String token,@PathVariable int userId){
        OrderData orderData = orderService.cancelOrderById(token,userId);
        ResponseDTO responseDTO = new ResponseDTO("order record canceled successfully !",orderData);
        return new ResponseEntity(responseDTO,HttpStatus.ACCEPTED);
    }
}
