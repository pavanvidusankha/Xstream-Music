package com.example.xstream.exceptions;

public class RoleServiceException extends RuntimeException{
    public RoleServiceException(){
        super(String.format("Error on Role Service layer"));
    }
}
