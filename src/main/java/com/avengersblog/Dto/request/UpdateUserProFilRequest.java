package com.avengersblog.Dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserProFilRequest {
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String password;

}
