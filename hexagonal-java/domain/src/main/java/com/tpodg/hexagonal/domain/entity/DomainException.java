package com.tpodg.hexagonal.domain.entity;

public class DomainException extends RuntimeException {
    public DomainException(String message) {
        super(message);
    }
}
