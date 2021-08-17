package com.example.xstream.exceptions;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.boot.context.properties.bind.validation.ValidationErrors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ExceptionHelper extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoObjectException(NoSuchElementException e){
        return new ResponseEntity<String> ("No entity exists in the database", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<String> handleBusinessLogicExceptions(CustomException e){
        return new ResponseEntity<String> ("There is an issue with the service layer", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<Object> ("Request body is not supported", HttpStatus.NOT_FOUND);

    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<Object> ("Request body is not acceptable", HttpStatus.UNSUPPORTED_MEDIA_TYPE);

    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request)
    {
        ValidationErrors validationErrors = null;
        if (ex.getRootCause() instanceof InvalidFormatException) {
            InvalidFormatException jacksonDataBindInvalidFormatException = (InvalidFormatException) ex.getRootCause();
        }
//        headers.add("X-Validation-Failure", "Request validation failed !");

        return new ResponseEntity<Object> ("Request body is not acceptable,Please check  whether the requestBody is in valid format", HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handle(Exception ex,
                                         HttpServletRequest request, HttpServletResponse response) {
        if (ex instanceof NullPointerException) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String> ("Request body is empty", HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> handleBusinessLogicExceptions(IllegalStateException e){
        return new ResponseEntity<String> ("There is an issue with the service layer", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
