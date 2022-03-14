package com.tpodg.hexagonal.domain.entity;

import java.util.Collection;

public record Post(
        Long id,
        String title,
        String content,
        Collection<Comment> comments
) {
}
