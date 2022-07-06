package com.example.bookstore.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
/**
 *  @Data:-it is convenient shortcut annotation that bundles features of @toString, @Getter &@Setter merthods.
 *  it generates all the boilerplate that is normally associated with simple POJOs
 */
@Data
public class OrderDTO {
    private  String token;
    private int quantity;
    @NotEmpty(message="address should not be empty")
    private String address;
    private int userId;
    private int bookId;
    private boolean cancel;
    private int price;
}
