package com.example.bookstore.service;

import com.example.bookstore.dto.BookDTO;
import com.example.bookstore.entity.BookData;
import com.example.bookstore.exception.BookStoreException;
import com.example.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IBookService{
    @Autowired
    private BookRepository bookRepository;

    @Override
    public BookData insert(BookDTO bookDTO) {
        BookData bookData=new BookData(bookDTO);
        return bookRepository.save(bookData);
    }

    @Override
    public List<BookData> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public BookData getBooksById(int id) {
        Optional<BookData> bookData=bookRepository.findById(id);
        if (bookData.isPresent()){
            return bookData.get();
        }else {
            throw new BookStoreException("Exception with id"+id+"does not exist!!");
        }
    }

    @Override
    public List<BookData> getBooksByName(String bookName) {
        List<BookData> findBook=bookRepository.findBookByName(bookName);
        if(findBook.isEmpty()){
            throw new BookStoreException(" Details for provided Book is not found");
        }
        return findBook;
    }

    @Override
    public List<BookData> getBooksByAutherName(String autherName) {
        List<BookData> findBook=bookRepository.findBookByAutherName(autherName);
        if(findBook.isEmpty()){
            throw new BookStoreException(" Book auther name is not found");
        }
        return findBook;
    }

    @Override
    public BookData updateBooksById(int id, BookDTO bookDTO) {
        Optional<BookData> bookData=bookRepository.findById(id);
        if (bookData.isPresent()){
            BookData updateData=new BookData(id,bookDTO);
            bookRepository.save(updateData);
            return updateData;
        }else {
            throw new BookStoreException("Bookdata record does not found");
        }
    }

    @Override
    public BookData updataBooksByQuantity(int id, int quantity) {
        Optional<BookData> bookData=bookRepository.findById(id);
        if (bookData.isPresent()){
            BookData bookData1=new BookData();
            bookData1.setQuantity(quantity);
            bookRepository.save(bookData1);
            return bookData1;
        }else {
            throw new BookStoreException("Bookdata record does not found");
        }
    }

    @Override
    public List<BookData> sortBookDataAsc() {
        return bookRepository.sortBookDataAsc();
    }

    @Override
    public List<BookData> sortBookDataDesc() {
        return bookRepository.sortBookDataDesc();
    }

    @Override
    public void deletebookData(int id) {
        Optional<BookData> bookData =bookRepository.findById(id);
        if (bookData.isPresent()) {
            bookRepository.deleteById(id);
        } else {
            throw new BookStoreException("Book record does not found");
        }
    }
}
