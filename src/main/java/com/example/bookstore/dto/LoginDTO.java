package com.example.bookstore.dto;

import lombok.Data;
/**
 *  @Data:-it is convenient shortcut annotation that bundles features of @toString, @Getter &@Setter merthods.
 *  it generates all the boilerplate that is normally associated with simple POJOs
 */
@Data
public class LoginDTO {
    private String email;
    private String password;
}
