package com.example.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
@Entity
@Data
public class OrderData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderId;
    private LocalDate date = LocalDate.now();
    private int price;
    private int quantity;
    private String address;
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserRegistrationData userRegistrationData;
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private BookData bookData;
    private boolean cancel;

    public OrderData() {
    }
    public OrderData(int orderId, int price, int quantity, String address, BookData bookData, UserRegistrationData userRegistrationData, boolean cancel) {
        this.orderId = orderId;
        this.price = price;
        this.quantity = quantity;
        this.address = address;
        this.bookData = bookData;
        this.userRegistrationData = userRegistrationData;
        this.cancel = cancel;
    }

    public OrderData(int price, int quantity, String address, BookData bookData, UserRegistrationData userRegistrationData, boolean cancel) {
        this.price = price;
        this.quantity = quantity;
        this.address = address;
        this.bookData = bookData;
        this.userRegistrationData = userRegistrationData;
        this.cancel = cancel;
    }
}
