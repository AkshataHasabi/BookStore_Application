package com.example.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
/**
 * @Data:-it is convenient shortcut annotation that bundles features of @toString, @Getter &@Setter merthods.
 * it generates all the boilerplate that is normally associated with simple POJOs
 * @Entity:-it tells hibernate to create table in DB
 * @Table:-specifies  the mapped table inDB
 */
@Entity
@Data
public class OrderData {
    //@ID:-specifies the primary key & id generation is set to auto
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderId;
    private LocalDate date = LocalDate.now();
    private int price;
    private int quantity;
    private String address;
    //@joincolumn:-refers to primary table
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
