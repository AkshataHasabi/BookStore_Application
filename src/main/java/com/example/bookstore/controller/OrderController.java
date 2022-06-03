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
        OrderData orderData = orderService.insert(orderdto);
        ResponseDTO responseDTO = new ResponseDTO("Order placed successfully !",orderData);
        return new ResponseEntity(responseDTO, HttpStatus.CREATED);
    }

    //get all order details
    @GetMapping("/getallorders")
    public ResponseEntity<ResponseDTO> getAllOrder(){
        List<OrderData> orderData = orderService.getAllOrder();
        ResponseDTO responseDTO = new ResponseDTO("All records retrieved successfully !",orderData);
        return new ResponseEntity(responseDTO,HttpStatus.OK);
    }

    //get all order details by id
    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseDTO> getOrderById(@PathVariable int id){
        OrderData orderData = orderService.getOrderById(id);
        ResponseDTO responseDTO = new ResponseDTO("get call successfully !",orderData);
        return new ResponseEntity(responseDTO,HttpStatus.OK);
    }

    //cancel order by orderid and userid
    @GetMapping("/cancelorder/{id}/{userId}")
    public ResponseEntity<ResponseDTO> cancelOrderById(@PathVariable int id,@PathVariable int userId){
        OrderData orderData = orderService.cancelOrderById(id,userId);
        ResponseDTO responseDTO = new ResponseDTO("order record canceled successfully !",orderData);
        return new ResponseEntity(responseDTO,HttpStatus.ACCEPTED);
    }

}
