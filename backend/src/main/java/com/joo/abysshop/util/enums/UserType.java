package com.joo.abysshop.util.enums;

public enum UserType {
    USER,
    ADMIN;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
