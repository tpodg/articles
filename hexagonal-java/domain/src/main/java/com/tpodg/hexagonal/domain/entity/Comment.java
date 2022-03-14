package com.tpodg.hexagonal.domain.entity;

import org.apache.commons.lang3.StringUtils;

public record Comment(
        Long id,
        String content,
        Long postId) {

    public Comment {
        if (postId == null) {
            throw new DomainException("Post id must be provided");
        }
        if (StringUtils.length(content) < 10) {
            throw new DomainException("Content must contain at least 10 characters");
        }
    }
}
