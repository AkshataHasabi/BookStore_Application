package com.example.bookstore.controller;

import com.example.bookstore.dto.BookDTO;
import com.example.bookstore.dto.ResponseDTO;
import com.example.bookstore.entity.BookData;
import com.example.bookstore.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private IBookService iBookService;

    //get all books details
    @GetMapping("/get/{token}")
    public ResponseEntity<ResponseDTO> getAllBooks(@PathVariable String token){
        List<BookData> bookData=iBookService.getAllBooks(token);
        ResponseDTO responseDTO=new ResponseDTO("Get call Success",bookData);
        return  new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    //get books details by id
    @GetMapping("id/{token}")
    public ResponseEntity<ResponseDTO> getBookById(@PathVariable String token){
        BookData bookData=iBookService.getBooksById(token);
        ResponseDTO responseDTO=new ResponseDTO("Get call Success for id successfull",bookData);
        return  new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    //get book details by bookname
    @GetMapping("/name/{bookName}")
    public ResponseEntity<ResponseDTO> getBookByName(@PathVariable String bookName){
        List<BookData> bookData=iBookService.getBooksByName(bookName);
        ResponseDTO responseDTO=new ResponseDTO("Get call Success for bookname successfull",bookData);
        return  new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    //get book details by authername
    @GetMapping("/nameofauther/{autherName}")
    public ResponseEntity<ResponseDTO> getBookByAutherName(@PathVariable String autherName){
        List<BookData> bookData=iBookService.getBooksByAutherName(autherName);
        ResponseDTO responseDTO=new ResponseDTO("Get call Success for authername successfull",bookData);
        return  new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    //create book details
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> addBooks(@Valid @RequestBody BookDTO bookDTO){
        String bookData =iBookService.insert(bookDTO);
        ResponseDTO responseDTO=new ResponseDTO("created book data succesfully",bookData);
        return  new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    //update book details by id
    @PutMapping("/update/{token}")
    public ResponseEntity<ResponseDTO> updateBooksById(@PathVariable String token,@Valid @RequestBody BookDTO bookDTO){
        BookData bookData=iBookService.updateBooksById(token,bookDTO);
        ResponseDTO responseDTO=new ResponseDTO("updated book data succesfully",bookData);
        return  new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    //update book details by quantity
    @PutMapping("/update/{token}/{quantity}")
    public ResponseEntity<ResponseDTO> updateBooksByQuantity(@PathVariable String token,@PathVariable int quantity){
        BookData bookData=iBookService.updataBooksByQuantity(token,quantity);
        ResponseDTO responseDTO=new ResponseDTO("updated book data succesfully",bookData);
        return  new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    //delete book details by id
    @DeleteMapping("/{token}")
    public ResponseEntity<ResponseDTO> deleteBookData(@PathVariable String token){
        iBookService.deletebookData(token);
        ResponseDTO responseDTO=new ResponseDTO("deleted succesfully",token);
        return  new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    //to sort bookdata in ascending order
    @GetMapping("/ascsort")
    public ResponseEntity<ResponseDTO> sortBookDataAsc(){
        List<BookData> bookData=iBookService.sortBookDataAsc();
        ResponseDTO responseDTO=new ResponseDTO("Bookdata in ascending order:",bookData);
        return  new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    //to sort bookdata in descending order
    @GetMapping("/dessort")
    public ResponseEntity<ResponseDTO> sortBookDataDesc(){
        List<BookData> bookData=iBookService.sortBookDataDesc();
        ResponseDTO responseDTO=new ResponseDTO("Bookdata in descending order:",bookData);
        return  new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
}
