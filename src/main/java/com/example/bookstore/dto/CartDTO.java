package com.example.bookstore.dto;

import com.example.bookstore.entity.BookData;
import com.example.bookstore.entity.UserRegistrationData;
import lombok.Data;
import javax.validation.constraints.NotNull;
/**
 *  @Data:-it is convenient shortcut annotation that bundles features of @toString, @Getter &@Setter merthods.
 *  it generates all the boilerplate that is normally associated with simple POJOs
 */
@Data
public class CartDTO {
    private String token;
    private int userId;
    private int bookId;
    @NotNull(message = "quantity should not be null")
    private int quantity;
}
