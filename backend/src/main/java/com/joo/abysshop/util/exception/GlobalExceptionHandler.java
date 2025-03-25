package com.joo.abysshop.util.exception;

import com.joo.abysshop.util.exception.account.DuplicateNicknameException;
import com.joo.abysshop.util.exception.account.DuplicateUsernameException;
import com.joo.abysshop.util.exception.auth.InvalidPasswordException;
import com.joo.abysshop.util.exception.auth.InvalidUsernameException;
import com.joo.abysshop.util.exception.point.InsufficientPointBalanceException;
import com.joo.abysshop.util.exception.product.ProductAlreadyExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(InvalidUsernameException.class)
    public ResponseEntity<String> handleInvalidUsernameException(InvalidUsernameException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<String> handleInvalidPasswordException(InvalidPasswordException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

    @ExceptionHandler(DuplicateUsernameException.class)
    public ResponseEntity<String> handleDuplicateUsernameException(DuplicateNicknameException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    @ExceptionHandler(DuplicateNicknameException.class)
    public ResponseEntity<String> handleDuplicateNicknameException(DuplicateNicknameException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    @ExceptionHandler(ProductAlreadyExistsException.class)
    public ResponseEntity<String> handleProductAlreadyExistsException(
        ProductAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    @ExceptionHandler(InsufficientPointBalanceException.class)
    public ResponseEntity<String> handleInsufficientPointBalanceException(
        InsufficientPointBalanceException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}

