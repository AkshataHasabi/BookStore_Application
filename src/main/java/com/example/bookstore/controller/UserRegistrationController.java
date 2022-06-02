package com.example.bookstore.controller;

import com.example.bookstore.dto.LoginDTO;
import com.example.bookstore.dto.ResponseDTO;
import com.example.bookstore.dto.UserRegistrationDTO;
import com.example.bookstore.entity.UserRegistrationData;
import com.example.bookstore.service.IUserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("user")
public class UserRegistrationController {
    @Autowired
    private IUserRegistrationService iUserRegistrationService;

    //get all users details
    @GetMapping("/get/{token}")
    public ResponseEntity<ResponseDTO> getAllUsers(@PathVariable String token){
        List<UserRegistrationData> userRegistrationData =iUserRegistrationService.getAllUsers(token);
        ResponseDTO responseDTO=new ResponseDTO("Get call Success",userRegistrationData);
        return  new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    //get users details by id
    @GetMapping("/{token}")
    public ResponseEntity<ResponseDTO> getUserById(@PathVariable String token){
        UserRegistrationData userRegistrationData=iUserRegistrationService.getUserById(token);
        ResponseDTO responseDTO=new ResponseDTO("Get call Success for id successfull",userRegistrationData);
        return  new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    //get users details by email
    @GetMapping("email/{token}")
    public ResponseEntity<ResponseDTO> getUserByEmail(@PathVariable String token){
            List<UserRegistrationData> userRegistrationData=iUserRegistrationService.getUserByEmail(token);
        ResponseDTO responseDTO=new ResponseDTO("Get call Success for id successfull",userRegistrationData);
        return  new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    //create users details
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> addUserRegistration(@Valid @RequestBody UserRegistrationDTO userRegistrationDTO){
        String userRegistrationData =iUserRegistrationService.createUser(userRegistrationDTO);
        ResponseDTO responseDTO=new ResponseDTO("created UserRegistration data succesfully",userRegistrationData);
        return  new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    //user must be login through email and password
    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody LoginDTO loginDTO){
        Optional<UserRegistrationData> userRegistrationData=iUserRegistrationService.login(loginDTO);
        if(userRegistrationData!=null){
            ResponseDTO responseDTO=new ResponseDTO("Login Succesfull",userRegistrationData);
            return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.ACCEPTED);
        }else {
            ResponseDTO responseDTO=new ResponseDTO("login Failed",userRegistrationData);
            return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.ACCEPTED);
        }
    }

    //update Users details
    @PutMapping("/update/{token}")
    public ResponseEntity<ResponseDTO> updateUser(@PathVariable String token,@Valid @RequestBody UserRegistrationDTO userRegistrationDTO){
        UserRegistrationData userRegistrationData=iUserRegistrationService.updateUser(token,userRegistrationDTO);
        ResponseDTO responseDTO=new ResponseDTO("updated user data succesfully",userRegistrationData);
        return  new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }


}
