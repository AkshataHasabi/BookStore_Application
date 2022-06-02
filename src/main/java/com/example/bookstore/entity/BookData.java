package com.example.bookstore.entity;

import com.example.bookstore.dto.BookDTO;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "BookData")
public class BookData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int bookId;
    private String bookName;
    private String autherName;
    private String bookDescription;
    private String bookImg;
    private int price;
    private int quantity;

    public BookData(int id, BookDTO bookDTO) {
        this.bookId=id;
        this.bookName=bookDTO.getBookName();
        this.autherName=bookDTO.getAutherName();
        this.bookDescription=bookDTO.getBookDescription();
        this.bookImg=bookDTO.getBookImg();
        this.price=bookDTO.getPrice();
        this.quantity=bookDTO.getQuantity();
    }

    public BookData(BookDTO bookDTO) {
        this.bookName=bookDTO.getBookName();
        this.autherName=bookDTO.getAutherName();
        this.bookDescription=bookDTO.getBookDescription();
        this.bookImg=bookDTO.getBookImg();
        this.price=bookDTO.getPrice();
        this.quantity=bookDTO.getQuantity();
    }
    public BookData() {
    }
}
