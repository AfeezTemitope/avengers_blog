package com.avengersblog.Dto.request.User;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//@NoArgsConstructor
//@AllArgsConstructor
public class UserRequest {
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String password;
}
