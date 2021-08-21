package com.example.xstream.exceptions;

public class UserServiceException extends RuntimeException{
    public UserServiceException(){
        super(String.format("Error on User Service layer"));
    }
}
