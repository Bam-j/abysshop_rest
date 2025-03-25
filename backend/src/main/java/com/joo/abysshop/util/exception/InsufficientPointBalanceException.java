package com.joo.abysshop.util.exception;

public class InsufficientPointBalanceException extends RuntimeException {

    public InsufficientPointBalanceException() {
        super("포인트 잔액이 부족합니다.");
    }

    public InsufficientPointBalanceException(String message) {
        super(message);
    }
}
