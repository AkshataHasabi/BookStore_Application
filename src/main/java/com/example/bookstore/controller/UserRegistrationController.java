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
@RequestMapping("/user")
/**
 * create a class name as UserRegistrationController
 */
public class UserRegistrationController {
    /**
     * 3) @AutoMapping :-
     *          @Autowiring feature of spring framework enables you to inject the object dependency implicitly.
     *          It internally uses setter or constructor injection.
     *
     * - Autowired  IUserRegistrationService interface, so we can inject its dependency here
     */
    @Autowired
    private IUserRegistrationService iUserRegistrationService;
    /**
     *get all data by using token
     * @param token:-i/p token in the form of string
     * @return fields with Http status
     */
    //get all users details
    @GetMapping("/get")
    public ResponseEntity<ResponseDTO> getAllUsers(@PathVariable String token){
        List<UserRegistrationData> userRegistrationData =iUserRegistrationService.getAllUsers(token);
        ResponseDTO responseDTO=new ResponseDTO("Get call Success",userRegistrationData);
        return  new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
    /**
     * get user by token
     * Ability to get a record by token
     */
    //get users details by id
    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseDTO> getUserById(@PathVariable int id){
        UserRegistrationData userRegistrationData=iUserRegistrationService.getUserById(id);
        ResponseDTO responseDTO=new ResponseDTO("Get call Success for id successfull",userRegistrationData);
        return  new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
    /**
     * get data for particular emailId
     * Ability to get a record by emailId
     */
    //get users details by email
    @GetMapping("email/{token}")
    public ResponseEntity<ResponseDTO> getUserByEmail(@PathVariable String token){
            List<UserRegistrationData> userRegistrationData=iUserRegistrationService.getUserByEmail(token);
        ResponseDTO responseDTO=new ResponseDTO("Get call Success for id successfull",userRegistrationData);
        return  new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
    /**
     * - Ability to save user details to repository
     * @apiNote- accepts the user data in JSON format and stores it in DB
     * @RequestBody:- it is used to bind http request body with a dto object in method parameter
     * @valid this will tell spring to go & validate the data passed into the controller.
     * @param userRegistrationDTO - user data
     * @return :- responseDTO
     */
    //create users details
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> addUserRegistration(@Valid @RequestBody UserRegistrationDTO userRegistrationDTO){
        String userRegistrationData =iUserRegistrationService.createUser(userRegistrationDTO);
        ResponseDTO responseDTO=new ResponseDTO("created UserRegistration data succesfully",userRegistrationData);
        return  new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    /**
     * ability to user login
     * @param loginDTO - email and password
     *@RequestBody:- it maps the body of the http request to an object
     * @return - login successfully msg show
     */
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
    /**
     * update  record data by id
     * @apiNote accepts the user data in JSON format and updates the user data having same Id from database
     * @valid this will tell spring to go & validate the data passed into the controller.
     * @RequestBody:- it maps the body of the http request to an object
     * @param token - represents user id
     * @param userRegistrationDTO - represents object of UserDto class
     * @return	updated user information in JSON format
     */
    //update Users details
    @PutMapping("/update/{token}")
    public ResponseEntity<ResponseDTO> updateUser(@PathVariable String token,@Valid @RequestBody UserRegistrationDTO userRegistrationDTO){
        UserRegistrationData userRegistrationData=iUserRegistrationService.updateUser(token,userRegistrationDTO);
        ResponseDTO responseDTO=new ResponseDTO("updated user data succesfully",userRegistrationData);
        return  new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/getToken/{email}")
    public ResponseEntity<ResponseDTO> getToken(@PathVariable String email){
        String generatedToken=iUserRegistrationService.getToken(email);
        ResponseDTO responseDTO=new ResponseDTO("Token for mail id sent on mail successfully",generatedToken);
        return new ResponseEntity(responseDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/getAll/{token}")
    public ResponseEntity<ResponseDTO> getAllUserDataByToken(@PathVariable String token)
    {
        List<UserRegistrationData> listOfUser = iUserRegistrationService.getAllUserDataByToken(token);
        ResponseDTO dto = new ResponseDTO("Data retrieved successfully (:",listOfUser);
        return new ResponseEntity(dto,HttpStatus.OK);
    }
}
