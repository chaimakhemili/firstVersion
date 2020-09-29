package com.workshop.formationBack.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFountException extends Exception{
    private static final long serialVersionUID = 1L;
    public ResourceNotFountException(String message) {
        super(message);
    }

}
