package com.tpodg.hexagonal.framework.input;

public record CommentDto(
        Long id,
        String content,
        Long postId) {
}
