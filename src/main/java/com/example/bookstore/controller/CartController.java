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

@RestController
@RequestMapping("cart")
public class CartController {
    @Autowired
    private ICartService iCartService;

    //get all cart details
    @GetMapping("/getall")
    public ResponseEntity<ResponseDTO> getAllCart(){
        List<CartData> cartData=iCartService.getAllCart();
        ResponseDTO responseDTO=new ResponseDTO("Get call Success",cartData);
        return  new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    //get cart details by id
    @GetMapping("give/{id}")
    public ResponseEntity<ResponseDTO> getCartById(@PathVariable int id){
        CartData cartData=iCartService.getCartById(id);
        ResponseDTO responseDTO=new ResponseDTO("Get call Success for id successfull",cartData);
        return  new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    //create cart details
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> addCart(@Valid @RequestBody CartDTO cartDTO){
        CartData cartData =iCartService.insert(cartDTO);
        ResponseDTO responseDTO=new ResponseDTO("created cart data succesfully",cartData);
        return  new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    //update cart details by id
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDTO> updateBooksById(@PathVariable int id,@Valid @RequestBody CartDTO cartDTO){
        CartData cartData=iCartService.updateCartById(id,cartDTO);
        ResponseDTO responseDTO=new ResponseDTO("updated cart data succesfully",cartData);
        return  new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    //delete cart details by id
    @DeleteMapping("/cartdelete/{id}")
    public ResponseEntity<ResponseDTO> deleteCartData(@PathVariable int id){
        iCartService.deleteCartData(id);
        ResponseDTO responseDTO=new ResponseDTO("deleted succesfully",id);
        return  new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
}
