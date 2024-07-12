package com.revature.Exception;

public class PersonInvalidNameException extends PersonException{
    public PersonInvalidNameException(String message) {
        super(message);
    }
    public PersonInvalidNameException(String message, Throwable cause) {
        super(message, cause);
    }
}
