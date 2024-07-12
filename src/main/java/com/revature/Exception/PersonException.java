package com.revature.Exception;

public class PersonException extends Exception{

    /**
     * throwing an exception and providing some useful description
     * for what happened
     */
    public PersonException(String message){
        super(message);
    }

    /**
     * overloaded constructor that will be used for exception chaining
     * (in the event that an exception triggers the throw of another exception,
     * we can provide information regarding both)
     */
    public PersonException(String message, Throwable cause){
        super(message, cause);
    }
}
