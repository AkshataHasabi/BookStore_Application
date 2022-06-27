package com.example.bookstore.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 *  @Data:-it is convenient shortcut annotation that bundles features of @toString, @Getter &@Setter merthods.
 *  it generates all the boilerplate that is normally associated with simple POJOs
 */
@Data
public class BookDTO {
//    @NotBlank(message = "book name should be not blank")
    private String bookName;
//    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$",message ="auther name pattern is invalid")
    private String autherName;
    @NotBlank(message = "bookdescription should not be blank")
    @NotEmpty(message = "bookdescription should not be empty")
    private String bookDescription;
    private String bookImg;
    @Min(value = 100,message = "minimum book price should be 100")
    private int price;
    private int quantity;
}
