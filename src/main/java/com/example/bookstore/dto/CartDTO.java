package com.example.bookstore.dto;

import com.example.bookstore.entity.BookData;
import com.example.bookstore.entity.UserRegistrationData;
import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class CartDTO {
    private int userId;
    private int bookId;
    @NotNull(message = "quantity should not be null")
    private int quantity;
}
