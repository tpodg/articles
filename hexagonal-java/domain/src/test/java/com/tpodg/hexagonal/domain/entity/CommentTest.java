package com.tpodg.hexagonal.domain.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CommentTest {

    @Test
    void validateContentLength() {
        DomainException exception = assertThrows(DomainException.class,
                () -> new Comment(null, "Too short", 1L));
        assertEquals("Content must contain at least 10 characters", exception.getMessage());
    }
}