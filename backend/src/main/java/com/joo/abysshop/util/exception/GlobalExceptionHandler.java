package com.joo.abysshop.util.exception;

import com.joo.abysshop.util.exception.account.DuplicateNicknameException;
import com.joo.abysshop.util.exception.account.DuplicateUsernameException;
import com.joo.abysshop.util.exception.account.SamePasswordException;
import com.joo.abysshop.util.exception.auth.InvalidPasswordException;
import com.joo.abysshop.util.exception.auth.InvalidUsernameException;
import com.joo.abysshop.util.exception.point.InsufficientPointBalanceException;
import com.joo.abysshop.util.exception.product.ProductAlreadyExistsException;
import jakarta.persistence.EntityNotFoundException;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleEntityNotFoundException(
        EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", e.getMessage()));
    }

    @ExceptionHandler(InvalidUsernameException.class)
    public ResponseEntity<Map<String, String>> handleInvalidUsernameException(
        InvalidUsernameException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(Map.of("message", e.getMessage()));
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<Map<String, String>> handleInvalidPasswordException(
        InvalidPasswordException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(Map.of("message", e.getMessage()));
    }

    @ExceptionHandler(DuplicateUsernameException.class)
    public ResponseEntity<Map<String, String>> handleDuplicateUsernameException(
        DuplicateNicknameException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("message", e.getMessage()));
    }

    @ExceptionHandler(SamePasswordException.class)
    public ResponseEntity<Map<String, String>> handleSamePasswordException(
        SamePasswordException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("message", e.getMessage()));
    }

    @ExceptionHandler(DuplicateNicknameException.class)
    public ResponseEntity<Map<String, String>> handleDuplicateNicknameException(
        DuplicateNicknameException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("message", e.getMessage()));
    }

    @ExceptionHandler(ProductAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleProductAlreadyExistsException(
        ProductAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("message", e.getMessage()));
    }

    @ExceptionHandler(InsufficientPointBalanceException.class)
    public ResponseEntity<Map<String, String>> handleInsufficientPointBalanceException(
        InsufficientPointBalanceException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(Map.of("message", e.getMessage()));
    }
}

