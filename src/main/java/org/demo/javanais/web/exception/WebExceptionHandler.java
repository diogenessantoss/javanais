package org.demo.javanais.web.exception;

import org.demo.javanais.domain.exception.JavanaisException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class WebExceptionHandler {

    @ExceptionHandler(JavanaisException.class)
    public ResponseEntity<ApiErreur> requeteException(Exception e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(ApiErreur.creerAvecWarn(badRequest, e.getMessage(), e), badRequest);
    }
}
