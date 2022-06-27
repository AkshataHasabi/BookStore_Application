package com.example.bookstore.service;

import com.example.bookstore.dto.LoginDTO;
import com.example.bookstore.dto.UserRegistrationDTO;
import com.example.bookstore.entity.UserRegistrationData;
import com.example.bookstore.exception.BookStoreException;
import com.example.bookstore.repository.UserRegistrationRepository;
import com.example.bookstore.util.EmailSenderService;
import com.example.bookstore.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
/**
 * @Service:-it is the annotation is used on the class level,
 * it annotates class to perform service tasks like executing bussiness logic & performing calculation.
 */
@Service
/**
 * Created UserService class to serve api calls done by controller layer
 */
public class UserRegistrationService implements IUserRegistrationService{
    /**
     * Autowired UserRegistrationRepository interface to inject its dependency here
     */
    @Autowired
    private UserRegistrationRepository userRegistrationRepository;
    /**
     * Autowired EmailSenderService interface to inject its dependency here
     */
    @Autowired
    private EmailSenderService mailService;
    /**
     * Autowired util interface to inject its dependency here
     */
    @Autowired
    private TokenUtil util;

    /**
     * create a method name as createUser
     * Ability to save user details to repository
     * @param userRegistrationDTO - user data
     * @return - save all data
     */
    //save the userregistration details in repository
    @Override
    public String createUser(UserRegistrationDTO userRegistrationDTO) {
        UserRegistrationData userRegistrationData= new UserRegistrationData(userRegistrationDTO);
        userRegistrationRepository.save(userRegistrationData);
        String token = util.createToken(userRegistrationData.getUserId());
        mailService.sendEmail(userRegistrationData.getEmail(), "Test Email", "Registered SuccessFully, hii: "
                +userRegistrationData.getFirstName()+"Please Click here to get data-> "
                +"http://localhost:8080/user/insert/"+token);
        return token;
    }
    /**
     * create a method name as getAllUsers
     * - Ability to get all user data by findAll() method
     * @param token - token
     * @return - all data
     */
    //pass path variable as token and get all userregistration details from repository.
    @Override
    public List<UserRegistrationData> getAllUsers(String token) {
        int id=util.decodeToken(token);
        Optional<UserRegistrationData> isContactPresent=userRegistrationRepository.findById(id);
        if(isContactPresent.isPresent()) {
            List<UserRegistrationData> listAddressBook=userRegistrationRepository.findAll();
            mailService.sendEmail("akshuh818@gmail.com", "Test Email", "Get your data with this token, hii: "
                    +isContactPresent.get().getFirstName()+"Please Click here to get data-> "
                    +"http://localhost:8081/user/get/"+token);
            return listAddressBook;
        }else {
            System.out.println("Exception ...Token not found!");
            return null;
        }
    }
    /**
     * create a method name as getUserById
     * - Ability to get user data by token
     * @param id - token
     * @return - user data by token
     */
    //get userregistration details from repository by id .
    @Override
    public UserRegistrationData getUserById(int id) {

        Optional<UserRegistrationData> getUser=userRegistrationRepository.findById(id);
        if(getUser.isPresent()){

            return getUser.get();
        }
        else {
            throw new BookStoreException("Record for provided userId is not found");
        }
    }
    /**
     * create a method name as getUserByEmail
     * - Ability get user data by email
     * @param token - user email
     * @return - user data
     */
    //get userregistration details from repository by email
    @Override
    public List<UserRegistrationData> getUserByEmail(String token) {
        List<UserRegistrationData> findEmail=userRegistrationRepository.findByEmail(token);
        if(findEmail.isEmpty()){
            throw new BookStoreException(" Details for provided user is not found");
        }
        return findEmail;
    }
    /**
     * create a method name as updateUser
     * @param token - user id
     * @param userRegistrationDTO - user data
     * @return - update user data
     */
    //update userregistration details by id
    @Override
    public UserRegistrationData updateUser(String token, UserRegistrationDTO userRegistrationDTO) {
        Integer id=util.decodeToken(token);
        Optional<UserRegistrationData> userRegistrationData = userRegistrationRepository.findById(id);
        if(userRegistrationData.isPresent()) {
            UserRegistrationData newBook = new UserRegistrationData(id,userRegistrationDTO);
            userRegistrationRepository.save(newBook);
            mailService.sendEmail(newBook.getEmail(), "Test Email", "Updated SuccessFully, hii: "
                    +newBook.getFirstName()+"Please Click here to get data of updated id-> "
                    +"http://localhost:8081/user/modify/"+token);
            return newBook;
        }
        throw new BookStoreException("User Details for id not found");
    }
    /**
     * create a method name as login
     * @param loginDTO - user login data (email, password)
     * @return - user data
     */
    //user has to login through email and password
    @Override
    public Optional<UserRegistrationData> login(LoginDTO loginDTO) {
        Optional<UserRegistrationData> userRegistrationData=userRegistrationRepository.findByEmailAndPassword(loginDTO.getEmail(),loginDTO.getPassword());
        if(userRegistrationData.isPresent()){
            return userRegistrationData;
        }else {
            return null;
        }
    }

    @Override
    public String getToken(String email) {
        Optional<UserRegistrationData> userRegistration=userRegistrationRepository.findByEmailId(email);
        String token=util.createToken(userRegistration.get().getUserId());
        mailService.sendEmail("akshuh818@gmail.com","Welcome User:  "+userRegistration.get().getFirstName(),"Token for changing password is :"+token);
        return token;
    }

    @Override
    public List<UserRegistrationData> getAllUserDataByToken(String token) {
        int id= Math.toIntExact(util.decodeToken(token));
        Optional<UserRegistrationData> isContactPresent=userRegistrationRepository.findById(id);
        if(isContactPresent.isPresent()) {
            List<UserRegistrationData> listOfUsers=userRegistrationRepository.findAll();
            mailService.sendEmail("akshuh818@gmail.com", "Test Email", "Get your data with this token, hii: "
                    +isContactPresent.get().getEmail()+"Please Click here to get data-> ");
            return listOfUsers;
        }else {
            System.out.println("Exception ...Token not found!");
            return null;	}
    }
}
