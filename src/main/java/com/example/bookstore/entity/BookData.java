package com.example.bookstore.entity;

import com.example.bookstore.dto.BookDTO;
import lombok.Data;

import javax.persistence.*;

/**
 * @Data:-it is convenient shortcut annotation that bundles features of @toString, @Getter &@Setter merthods.
 * it generates all the boilerplate that is normally associated with simple POJOs
 * @Entity:-it tells hibernate to create table in DB
 * @Table:-specifies  the mapped table inDB
 */
@Entity
@Data
@Table(name = "BookData")
public class BookData {
    //@ID:-specifies the primary key & id generation is set to auto
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
