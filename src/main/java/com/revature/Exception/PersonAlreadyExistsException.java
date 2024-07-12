package com.revature.Exception;

import com.revature.Model.Person;

public class PersonAlreadyExistsException extends PersonException {
    public PersonAlreadyExistsException(String message) {
        super(message);
    }

    public PersonAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
