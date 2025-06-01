package com.joo.abysshop.util.exception.account;

public class SamePasswordException extends RuntimeException {

    public SamePasswordException(String message) {
        super(message);
    }
}
