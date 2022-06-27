package com.example.bookstore.service;

import com.example.bookstore.dto.BookDTO;
import com.example.bookstore.entity.BookData;
import com.example.bookstore.exception.BookStoreException;
import com.example.bookstore.repository.BookRepository;
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
 * Created BookService class to serve api calls done by controller layer
 */
public class BookService implements IBookService{
    /**
     * Autowired BookRepository interface to inject its dependency here
     */
    @Autowired
    private BookRepository bookRepository;
    /**
     * Autowired util interface to inject its dependency here
     */
    @Autowired
    private TokenUtil util;
    /**
     * create a method name as insert
     * Ability to save book details to repository
     * @param bookDTO - all book data
     * @return - save all data
     */
    //save book details to repo
    @Override
    public String insert(BookDTO bookDTO) {
        BookData bookData=new BookData(bookDTO);
        bookRepository.save(bookData);
        String token = util.createToken(bookData.getBookId());
        return token;
    }
    /**
     * create a method name as getAllBooks
     * Ability to get all book data by findAll() method
     * @param  - book id
     * @return - all book data
     */
    //get all bookdata by findAll method
    @Override
    public List<BookData> getAllBooks() {

            List<BookData> listOfBooks=bookRepository.findAll();
            return listOfBooks;
            }
    /**
     * create a method name as getBooksById
     * - Ability to get book data by id
     * @param token - book id
     * @return - book data by id
     */
    //get bookdata by id
    @Override
    public BookData getBooksById(int id) {
        Optional<BookData> bookData=bookRepository.findById(id);
        if (bookData.isPresent()){
            return bookData.get();
        }else {
            throw new BookStoreException("Exception with id"+id+"does not exist!!");
        }
    }
    /**
     * create a method name as getBooksByName
     * - Ability to get book data by bookName
     * @param bookName - book Name
     * @return - all book data by bookname
     */
    //get bookdata by bookname
    @Override
    public List<BookData> getBooksByName(String bookName) {
//        List<BookData> findBook=
              return bookRepository.findBookByName(bookName);
//        if(findBook.isEmpty()){
//            throw new BookStoreException(" Details for provided Book is not found");
//        }
//        return findBook;
    }
    /**
     * create a method name as getBooksByAutherName
     * - Ability to get book data by book auther name
     * @param autherName - book authername
     * @return - all book data by id
     */
    //get bookdata by authername
    @Override
    public List<BookData> getBooksByAutherName(String autherName) {
        List<BookData> findBook=bookRepository.findBookByAutherName(autherName);
        if(findBook.isEmpty()){
            throw new BookStoreException(" Book auther name is not found");
        }
        return findBook;
    }
    /**
     * create a method name as updateBooksById
     * Ability to update book data for particular id
     * @param id - book id
     * @param bookDTO - book data
     * @return - updated book information in JSON format
     */
    //update bookdata by id
    @Override
    public BookData updateBooksById(int id , BookDTO bookDTO) {
        Optional<BookData> bookData=bookRepository.findById(id);
        if (bookData.isPresent()){
            BookData updateData=new BookData(id,bookDTO);
            bookRepository.save(updateData);
            return updateData;
        }else {
            throw new BookStoreException("Bookdata record does not found");
        }
    }
    /**
     * create a method name as updateBookByQuantity
     * Ability to update book data for particular id and quantity
     * @param token - book id
     * @param quantity - book quantity
     * @return - updated book information in JSON format
     */
    //update bookdata by quantity
    @Override
    public BookData updataBooksByQuantity(String token, int quantity) {
        int id=util.decodeToken(token);
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
    /**
     * create a method name as sortBookDataAsc
     * ability to get book data in ascending order
     * @return - all data in ascending order
     */
    //sort bookdata in ascending order
    @Override
    public List<BookData> sortBookDataAsc() {
        return bookRepository.sortBookDataAsc();
    }
    /**
     * create a method name as sortBookDataDesc
     * ability to get book data in descending order
     * @return - all data in descending order
     */
    //sort bookdata in descending order
    @Override
    public List<BookData> sortBookDataDesc() {
        return bookRepository.sortBookDataDesc();
    }
    /**
     * create a method name as deletebookData
     * ability to delete data by particular book id
     * @param token - book id
     * @return -void type is there if id is not present it will send ecxception message
     */
    //delete bookdata by id from repository
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
