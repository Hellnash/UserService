package com.lcwd.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(){
        super("Resource is not found in server !!");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
