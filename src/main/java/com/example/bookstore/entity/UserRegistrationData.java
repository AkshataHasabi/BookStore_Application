package com.example.bookstore.entity;

import com.example.bookstore.dto.UserRegistrationDTO;
import lombok.Data;

import javax.persistence.*;
/**
 * @Data:-it is convenient shortcut annotation that bundles features of @toString, @Getter &@Setter merthods.
 * it generates all the boilerplate that is normally associated with simple POJOs
 * @Entity:-it tells hibernate to create table in DB
 * @Table:-specifies  the mapped table inDB
 */
@Data
@Entity
@Table(name = "UserData")
public class UserRegistrationData {
    //@ID:-specifies the primary key & id generation is set to auto
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String password;

    public UserRegistrationData(UserRegistrationDTO userRegistrationDTO) {
        this.firstName = userRegistrationDTO.getFirstName();
        this.lastName = userRegistrationDTO.getLastName();
        this.email = userRegistrationDTO.getEmail();
        this.address = userRegistrationDTO.getAddress();
        this.password = userRegistrationDTO.getPassword();
    }

    public UserRegistrationData(int id, UserRegistrationDTO userRegistrationDTO) {
        this.userId=id;
        this.firstName = userRegistrationDTO.getFirstName();
        this.lastName = userRegistrationDTO.getLastName();
        this.email = userRegistrationDTO.getEmail();
        this.address = userRegistrationDTO.getAddress();
        this.password = userRegistrationDTO.getPassword();
    }

    public UserRegistrationData() {
    }
}
