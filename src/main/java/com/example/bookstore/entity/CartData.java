package com.example.bookstore.entity;

import com.example.bookstore.dto.CartDTO;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import javax.persistence.*;
/**
 * @Data:-it is convenient shortcut annotation that bundles features of @toString, @Getter &@Setter merthods.
 * it generates all the boilerplate that is normally associated with simple POJOs
 * @Entity:-it tells hibernate to create table in DB
 * @Table:-specifies  the mapped table inDB
*/
//@JsonFormat(shape = JsonFormat.Shape.ARRAY)
//@JsonPropertyOrder({ "userId", "bookId", "quantity"})
@Data
@Entity
@Table(name = "CartData")
public class CartData {
    //@ID:-specifies the primary key & id generation is set to auto
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cartId;
    //@joincolumn:-refers to primary table
   @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserRegistrationData user;
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private BookData bookData;
    private int quantity;

    public CartData() {
    }

    public CartData(int cartId,UserRegistrationData user, BookData bookData, int quantity) {
        this.cartId=cartId;
        this.user = user;
        this.bookData = bookData;
        this.quantity = quantity;
    }

    public CartData(UserRegistrationData user, BookData bookData, int quantity) {
        this.user= user;
        this.bookData = bookData;
        this.quantity = quantity;
    }
}
