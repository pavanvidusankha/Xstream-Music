package com.example.xstream.exceptions;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.boot.context.properties.bind.validation.ValidationErrors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ExceptionHelper extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleNoObjectException(NoSuchElementException e){
        return new ResponseEntity<> ("No entity exists in the database ", HttpStatus.NOT_FOUND);
    }

    @Override
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<> ("Missing path variable ", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyListException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> handleEmptyException(NoSuchElementException e){
        return new ResponseEntity<> ("No data in DB to return ", HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(CustomException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleBusinessLogicExceptions(CustomException e){
        return new ResponseEntity<> ("There is an issue with the  user service layer ", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<> ("Request body is not supported ", HttpStatus.NOT_FOUND);

    }

    @Override
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<> ("Request body is not acceptable", HttpStatus.UNSUPPORTED_MEDIA_TYPE);

    }

    @Override
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request)
    {
        ValidationErrors validationErrors = null;
        if (ex.getRootCause() instanceof InvalidFormatException) {
            InvalidFormatException jacksonDataBindInvalidFormatException = (InvalidFormatException) ex.getRootCause();
        }
//        headers.add("X-Validation-Failure", "Request validation failed !");

        return new ResponseEntity<> ("Request body is not acceptable,Please check  whether the requestBody is in valid format ", HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public ResponseEntity<Object> handle(Exception ex,
                                         HttpServletRequest request, HttpServletResponse response) {
        if (ex instanceof NullPointerException) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<> ("Request body is empty or invalid parameter", HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(NoItemFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleNoItemException(NoItemFoundException e){
        return new ResponseEntity<> ("No item found in the database ", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RoleServiceException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleNoItemException(RoleServiceException e){
        return new ResponseEntity<> ("There is an issue with role service layer", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserServiceException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleNoItemException(UserServiceException e){
        return new ResponseEntity<> ("There is an issue with user auth service layer", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<> ("Request media type is unsupported", HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleBusinessLogicExceptions(IllegalStateException e){
        return new ResponseEntity<> ("There is an issue with the provided parameters.Please check", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
