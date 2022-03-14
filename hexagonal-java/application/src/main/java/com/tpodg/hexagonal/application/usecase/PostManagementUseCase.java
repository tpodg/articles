package com.tpodg.hexagonal.application.usecase;

import com.tpodg.hexagonal.domain.entity.Comment;
import com.tpodg.hexagonal.domain.entity.Post;

import java.util.Collection;

public interface PostManagementUseCase {
    Post create(String title, String content);

    Post persist(Post post);

    Post retrieve(Long id);

    Comment addComment(Comment comment);

    Collection<Comment> listComments(Long id);
}
