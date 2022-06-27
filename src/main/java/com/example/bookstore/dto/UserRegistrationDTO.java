package com.example.bookstore.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
/**
 *  @Data:-it is convenient shortcut annotation that bundles features of @toString, @Getter &@Setter merthods.
 *  it generates all the boilerplate that is normally associated with simple POJOs
 */
@Data
public class UserRegistrationDTO {
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$",message ="first name pattern is invalid")
    private String firstName;
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$",message ="first name pattern is invalid")
    private String lastName;
//    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message="Email pattern is invalid")
    private String email;
//    @NotBlank(message = "address should not be empty")
    private String address;
//    @Pattern(regexp = "(.{8,}[A-Z0-9]?[+.$#@!&%*]?)",message = "password pattern is invalid")
    private String password;
}
