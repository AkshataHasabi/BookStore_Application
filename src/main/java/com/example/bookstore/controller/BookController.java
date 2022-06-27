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
/**
 *  1) @RestController :-
 *           @RestController is used for making restful web services with the help of the @RestController annotation.
 *           This annotation is used at the class level and allows the class to handle the requests made by the client
 * 2) @RequestMapping :-
 *           @RequestMapping used to map web requests onto specific handler classes and/or handler methods.
 *           RequestMapping can be applied to the controller class as well as methods
 *
 * - Created controller so that we can perform rest api calls
 */
@CrossOrigin
@RestController
@RequestMapping("/book")
public class BookController {
    /**
     * 3) @AutoMapping :-
     *          @Autowiring feature of spring framework enables you to inject the object dependency implicitly.
     *          It internally uses setter or constructor injection.
     *
     * - Autowired IBookService interface so we can inject its dependency here
     */
    @Autowired
    private IBookService iBookService;
    /**
     * 4) @PostMapping :-
     *           @PostMapping annotation maps HTTP POST requests onto specific handler methods.
     *           It is a composed annotation that acts as a shortcut for @RequestMapping(method = RequestMethod. POST)
     *
     * 5) @RequestBody :-
     *            @RequestBody annotation is applicable to handler methods of Spring controllers.
     *            This annotation indicates that Spring should deserialize a request body into an object.
     *            This object is passed as a handler method parameter.
     */

    /**
     * - Ability to get all book' data by findAll() method
     * @return :- showing all data
     */
    //get all books details
    @GetMapping("/get")
    public ResponseEntity<ResponseDTO> getAllBooks(){
        List<BookData> bookData=iBookService.getAllBooks();
        ResponseDTO responseDTO=new ResponseDTO("Get call Success",bookData);
        return  new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
    /**
     * 6) @PathVariable :-
     *           @PathVariable is a Spring annotation which indicates that a method parameter should be bound to a URI template variable. It has the following optional elements: name - name of the path variable to bind to.
     *           required - tells whether the path variable is required.
     * - Ability to get book data by id
     * @param id - book id
     * @return -book information with same bookId in JSON format
     */
    //get books details by id
    @GetMapping("id/{id}")
    public ResponseEntity<ResponseDTO> getBookById(@PathVariable int id){
        BookData bookData=iBookService.getBooksById(id);
        ResponseDTO responseDTO=new ResponseDTO("Get call Success for id successfull",bookData);
        return  new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
    /**
     * create a method name as getBookByName
     * Ability to get book by book name
     * @param bookName - book name
     * @return - book data
     */
    //get book details by bookname
    @GetMapping("/name/{bookName}")
    public ResponseEntity<ResponseDTO> getBookByName(@PathVariable String bookName){
        List<BookData> bookData=iBookService.getBooksByName(bookName);
        ResponseDTO responseDTO=new ResponseDTO("Get call Success for bookname successfull",bookData);
        return  new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
    /**
     * Ability to get book data by author name.
     * @param autherName - put author-name in url
     * @return - book data by author name
     */
    //get book details by authername
    @GetMapping("/nameofauther/{autherName}")
    public ResponseEntity<ResponseDTO> getBookByAutherName(@PathVariable String autherName){
        List<BookData> bookData=iBookService.getBooksByAutherName(autherName);
        ResponseDTO responseDTO=new ResponseDTO("Get call Success for authername successfull",bookData);
        return  new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
     /**Ability to save book details to repository
     * @apiNote- accepts the book data in JSON format and stores it in DB
      * @valid this will tell spring to go & validate the data passed into the controller.
      * @RequestBody:- it maps the body of the http request to an object
     * @param bookDTO - book data
     * @return :- responseDTO
     */
    //create book details
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> addBooks(@Valid @RequestBody BookDTO bookDTO){
        String bookData =iBookService.insert(bookDTO);
        ResponseDTO responseDTO=new ResponseDTO("created book data succesfully",bookData);
        return  new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
    /**
     * 7) @PutMapping :-
     *            @PutMapping Annotation for mapping HTTP PUT requests onto specific handler methods.
     *            Specifically, @PutMapping is a composed annotation that acts as a shortcut for @RequestMapping(method = RequestMethod.PUT).
     * @RequestBody:- it is used to bind http request body with a dto object in method parameter
     * Ability to update book data for particular id
     * @apiNote - accepts the book data in JSON format and updates the book having same bookId from database
     * @param id - book id
     * @valid this will tell spring to go & validate the data passed into the controller.
     * @param bookDTO -  represents object of bookDTO class
     * @return - updated book information in JSON format
     */
    //update book details by id
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDTO> updateBooksById(@PathVariable int id ,@Valid @RequestBody BookDTO bookDTO){
        BookData bookData=iBookService.updateBooksById(id,bookDTO);
        ResponseDTO responseDTO=new ResponseDTO("updated book data succesfully",bookData);
        return  new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
    /**
     * 7) @PutMapping :-
     *            @PutMapping Annotation for mapping HTTP PUT requests onto specific handler methods.
     *            Specifically, @PutMapping is a composed annotation that acts as a shortcut for @RequestMapping(method = RequestMethod.PUT).
     *
     * Ability to update book data for particular id and book quantity
     * @apiNote - accepts the book data in JSON format and updates the book having same bookId from database
     * @param token - book id
     * @param quantity -  book quantity
     * @return - updated book information in JSON format
     */
    //update book details by quantity
    @PutMapping("/update/{token}/{quantity}")
    public ResponseEntity<ResponseDTO> updateBooksByQuantity(@PathVariable String token,@PathVariable int quantity){
        BookData bookData=iBookService.updataBooksByQuantity(token,quantity);
        ResponseDTO responseDTO=new ResponseDTO("updated book data succesfully",bookData);
        return  new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
    /**
     * - Ability to delete book data for particular id
     * @apiNote - accepts the bookId and deletes the data of that book from DB
     * @param token - represents book id
     * @return -  bookId and Acknowledgment message
     */
    //delete book details by id
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteBookData(@PathVariable int id){
        iBookService.deletebookData(id);
        ResponseDTO responseDTO=new ResponseDTO("deleted succesfully",id);
        return  new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
    /**
     * ability to get book data in ascending order
     * @return - data in ascending order
     */
    //to sort bookdata in ascending order
    @GetMapping("/ascsort")
    public ResponseEntity<ResponseDTO> sortBookDataAsc(){
        List<BookData> bookData=iBookService.sortBookDataAsc();
        ResponseDTO responseDTO=new ResponseDTO("Bookdata in ascending order:",bookData);
        return  new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
    /**
     * ability to get book data in descending order
     * @return - data in descending order
     */
    //to sort bookdata in descending order
    @GetMapping("/dessort")
    public ResponseEntity<ResponseDTO> sortBookDataDesc(){
        List<BookData> bookData=iBookService.sortBookDataDesc();
        ResponseDTO responseDTO=new ResponseDTO("Bookdata in descending order:",bookData);
        return  new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
}
