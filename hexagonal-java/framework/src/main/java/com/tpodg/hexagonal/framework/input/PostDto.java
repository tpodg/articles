package com.tpodg.hexagonal.framework.input;

public record PostDto(
        Long id,
        String title,
        String content) {
}
