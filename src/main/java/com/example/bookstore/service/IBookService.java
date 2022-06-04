package com.example.bookstore.service;

import com.example.bookstore.dto.BookDTO;
import com.example.bookstore.dto.UserRegistrationDTO;
import com.example.bookstore.entity.BookData;
import com.example.bookstore.entity.UserRegistrationData;

import java.util.List;

public interface IBookService {
    String insert(BookDTO bookDTO);
    List<BookData> getAllBooks(String token);
    BookData getBooksById(String token);
    List<BookData> getBooksByName(String bookName);
    List<BookData> getBooksByAutherName(String autherName);
    BookData updateBooksById(String token,BookDTO bookDTO);
    BookData updataBooksByQuantity(String token,int quantity);
    List<BookData> sortBookDataAsc();
    List<BookData> sortBookDataDesc();
    void deletebookData(String token);
}
