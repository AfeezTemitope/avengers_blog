package com.avengersblog.Exceptions.UserExceptions;

public class UserNotFoundException extends Throwable {
    public UserNotFoundException(String couldNotIdentifyUser) {
        super(couldNotIdentifyUser);
    }
}
