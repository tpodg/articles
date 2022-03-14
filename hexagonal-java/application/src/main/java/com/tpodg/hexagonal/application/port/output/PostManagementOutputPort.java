package com.tpodg.hexagonal.application.port.output;

import com.tpodg.hexagonal.domain.entity.Comment;
import com.tpodg.hexagonal.domain.entity.Post;

import java.util.Collection;

public interface PostManagementOutputPort {
    Post persist(Post post);

    Post retrieve(Long id);

    Comment addComment(Comment comment);

    Collection<Comment> listComments(Long id);
}
