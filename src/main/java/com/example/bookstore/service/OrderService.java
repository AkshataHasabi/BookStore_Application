package com.example.bookstore.service;

import com.example.bookstore.dto.OrderDTO;
import com.example.bookstore.entity.BookData;
import com.example.bookstore.entity.OrderData;
import com.example.bookstore.entity.UserRegistrationData;
import com.example.bookstore.exception.BookStoreException;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.OrderRepository;
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
 * Created OrderService class to serve api calls done by controller layer
 */
public class OrderService implements IOrderService {
    /**
     * Autowired BookRepository interface to inject its dependency here
     */
    @Autowired
    private BookRepository bookRepository;
    /**
     * Autowired UserRegistrationRepository interface to inject its dependency here
     */
    @Autowired
    private UserRegistrationRepository userRegistrationRepository;
    /**
     * Autowired OrderRepository interface to inject its dependency here
     */
    @Autowired
    private OrderRepository orderRepository;
    /**
     * Autowired util interface to inject its dependency here
     */
    @Autowired
    private TokenUtil util;
    /**
     * Autowired EmailSenderService interface to inject its dependency here
     */
    @Autowired
    private EmailSenderService mailService;

    /**
     * create a method name as insert
     * Ability to save order details to repository
     *
     * @param orderDTO - order data
     * @return - save all data
     */
    //save  order details in repository  method.
    @Override
    public OrderData insert(OrderDTO orderDTO) {
        Optional<BookData> book = bookRepository.findById(orderDTO.getBookId());
        Long userId = Long.valueOf(util.decodeToken(orderDTO.getToken()));
        Optional<UserRegistrationData> user = userRegistrationRepository.findById(Math.toIntExact(userId));
        if (book.isPresent() && user.isPresent()) {
            if (orderDTO.getQuantity() <= book.get().getQuantity()) {
                int quantity = book.get().getQuantity() - orderDTO.getQuantity();
                book.get().setQuantity(quantity);
                bookRepository.save(book.get());
                int totalPrice = book.get().getPrice() * orderDTO.getQuantity();
                OrderData newOrder = new OrderData(totalPrice, orderDTO.getQuantity(), orderDTO.getAddress(), book.get(), user.get(), orderDTO.isCancel());
                orderRepository.save(newOrder);
//                String token = util.createToken(newOrder.getOrderId());
//                mailService.sendEmail(newOrder.getUserRegistrationData().getEmail(), "Test Email", "Registered SuccessFully, hii: "
//                        +newOrder.getOrderId()+"Please Click here to get data-> "
//                        +"http://localhost:8080/order/insert/"+token);
                log.info("Order record inserted successfully");
                return newOrder;
            } else {
                throw new BookStoreException("Requested quantity is out of stock");
            }
        } else {
            throw new BookStoreException("Book or User doesn't exists");
        }
    }

    /**
     * create a method name as getAllOrder
     * - Ability to get all order data by findAll() method
     *
     * @return - all data
     */
    //get all order details method
    //return type is list
    @Override
    public List<OrderData> getAllOrder() {

        List<OrderData> listOrderData = orderRepository.findAll();
//            log.info("ALL order records retrieved successfully");
//            mailService.sendEmail("akshuh818@gmail.com", "Test Email", "Get your data with this token, hii: "
//                    +orderData.get().getUserRegistrationData().getEmail()+"Please Click here to get all data-> "
//                    +"http://localhost:8080/order/get/"+token);
        return listOrderData;
//        }else {
//            System.out.println("Exception ...Token not found!");
//            return null;
//        }
    }

    /**
     * create a method name as getOrderById
     * - Ability to get order data by Id
     *
     * @param token - order id
     * @return - order data by id
     */
    //get all order details by id
    @Override
    public OrderData getOrderById(String token) {
        Integer id = util.decodeToken(token);
        Optional<OrderData> orderData = orderRepository.findById(id);
        if (orderData.isPresent()) {
            log.info("Order record retrieved successfully for id " + id);
            mailService.sendEmail("akshuh818@gmail.com", "Test Email", "Get your data with this token, hii: "
                    + orderData.get().getUserRegistrationData().getEmail() + "Please Click here to get data-> "
                    + "http://localhost:8080/order/get/" + token);
            return orderData.get();
        } else {
            throw new BookStoreException("Order doesn't exists");
        }
    }

    /**
     * create a method name as cancelOrderById
     *
     * @param token - order id
     * @return - order id cancel
     */
    //cancel order by orderid and userid
    @Override
    public OrderData cancelOrderById(String token, int userId) {
        //orderid,userid
        Integer id = util.decodeToken(token);
        Optional<OrderData> order = orderRepository.findById(id);
        Optional<UserRegistrationData> user = userRegistrationRepository.findById(userId);
        if (order.isPresent() && user.isPresent()) {
            order.get().setCancel(true);
            orderRepository.save(order.get());
            mailService.sendEmail(order.get().getUserRegistrationData().getEmail(), "Test Email", "canceled order SuccessFully, hii: "
                    + order.get().getOrderId() + "Please Click here to get data of updated id-> "
                    + "http://localhost:8080/order/cancel/" + token);
            return order.get();
        } else {
            throw new BookStoreException("Order Record doesn't exists");
        }
    }

    @Override
    public OrderData deleteOrderRecord(Integer id) {
        Optional<OrderData> order = orderRepository.findById(id);
        if (order.isPresent()) {
            orderRepository.deleteById(id);
            return order.get();

        } else {
            throw new BookStoreException("Order Record doesn't exists");
        }
    }
    @Override
    public Integer totalPrice() {
        List<OrderData> order = orderRepository.findAll();
        Integer totalPrice = 0;
        for(int i =0; i<order.size();i++){
            totalPrice +=order.get(i).getPrice();
        }
        return totalPrice;
    }
}
