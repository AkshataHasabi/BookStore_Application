package com.example.bookstore.exception;

import com.example.bookstore.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @ControllerAdvice:this annotation is a specialization of the @component annotation,
 * which allows to handle exception accross the whole application in one global handling component.
 */
@ControllerAdvice
public class BookStoreExceptionHandler {
    /**
     * this annotation used to handle the specific exception & sending the custom responses to the client.
     * @param methodArgumentNotValidException=methodArgumentNotValidException
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO> handleException(MethodArgumentNotValidException methodArgumentNotValidException){
        List<ObjectError> errorList=methodArgumentNotValidException.getBindingResult().getAllErrors();
        List<String> errMessage=errorList.stream().map(objectError -> objectError.getDefaultMessage()).collect(Collectors.toList());
        ResponseDTO responseDTO=new ResponseDTO("Exception while processing REST request", errMessage);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookStoreException.class)
    public ResponseEntity<ResponseDTO> handleCustomException(BookStoreException bookStoreException){
        ResponseDTO responseDTO=new ResponseDTO("Exception while processing REST Request", bookStoreException.getMessage());
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.BAD_REQUEST);
    }
}
