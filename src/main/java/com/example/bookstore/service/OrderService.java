package com.example.bookstore.service;

import com.example.bookstore.dto.OrderDTO;
import com.example.bookstore.entity.BookData;
import com.example.bookstore.entity.OrderData;
import com.example.bookstore.entity.UserRegistrationData;
import com.example.bookstore.exception.BookStoreException;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.OrderRepository;
import com.example.bookstore.repository.UserRegistrationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OrderService implements IOrderService{
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRegistrationRepository userRegistrationRepository;
    @Autowired
    private OrderRepository orderRepository;

    //save  order details in repository  method.
    @Override
    public OrderData insert(OrderDTO orderDTO) {
        Optional<BookData> book = bookRepository.findById(orderDTO.getBookId());
        Optional<UserRegistrationData> user = userRegistrationRepository.findById(orderDTO.getUserId());
        if (book.isPresent() && user.isPresent()) {
            if (orderDTO.getQuantity()<= book.get().getQuantity()) {
                int quantity = book.get().getQuantity()-orderDTO.getQuantity();
                book.get().setQuantity(quantity);
                bookRepository.save(book.get());
                OrderData newOrder = new OrderData(book.get().getPrice(), orderDTO.getQuantity(), orderDTO.getAddress(), book.get(), user.get(), orderDTO.isCancel());
                orderRepository.save(newOrder);
                log.info("Order record inserted successfully");
                return newOrder;
            } else {
                throw new BookStoreException("Requested quantity is out of stock");
            }
        } else {
            throw new BookStoreException("Book or User doesn't exists");
        }
    }

    //get all order details method
    //return type is list
    @Override
    public List<OrderData> getAllOrder() {
        log.info("ALL order records retrieved successfully");
        return orderRepository.findAll();
    }

    //get all order details by id
    @Override
    public OrderData getOrderById(int id) {
        Optional<OrderData> orderData = orderRepository.findById(id);
        if (orderData.isPresent()) {
            log.info("Order record retrieved successfully for id " + id);
            return orderData.get();

        } else {
            throw new BookStoreException("Order doesn't exists");
        }
    }

   //update order by id
    @Override
    public OrderData updateOrderById(int id, OrderDTO orderDTO) {
        Optional<OrderData> order = orderRepository.findById(id);
        Optional<BookData> book = bookRepository.findById(orderDTO.getBookId());
        Optional<UserRegistrationData> user = userRegistrationRepository.findById(orderDTO.getUserId());
        if (order.isPresent()) {
            if (book.isPresent() && user.isPresent()) {
                if (orderDTO.getQuantity() <= book.get().getQuantity()) {
                    int quantity = book.get().getQuantity()-orderDTO.getQuantity();
                    book.get().setQuantity(quantity);
                    bookRepository.save(book.get());
                    OrderData orderData = new OrderData(id, book.get().getPrice(), orderDTO.getQuantity(), orderDTO.getAddress(), book.get(), user.get(), orderDTO.isCancel());
                    orderRepository.save(orderData);
                    log.info("Order record updated successfully for id " + id);
                    return orderData;
                } else {
                    throw new BookStoreException("Requested quantity is not available");
                }
            } else {
                throw new BookStoreException("Book or User doesn't exists");

            }

        } else {
            throw new BookStoreException("Order Record doesn't exists");
        }
    }

    //delete order by id
    @Override
    public OrderData deleteOrderData(int id) {
        Optional<OrderData> orderData = orderRepository.findById(id);
        if (orderData.isPresent()) {
            orderRepository.deleteById(id);
            log.info("Order  deleted successfully for id " + id);
            return orderData.get();

        } else {
            throw new BookStoreException("Order Record doesn't exists");
        }
    }
}
