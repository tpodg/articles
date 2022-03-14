package com.tpodg.hexagonal.application.port.input;

import com.tpodg.hexagonal.application.port.output.PostManagementOutputPort;
import com.tpodg.hexagonal.application.usecase.PostManagementUseCase;
import com.tpodg.hexagonal.domain.entity.Comment;
import com.tpodg.hexagonal.domain.entity.Post;

import java.util.Collection;
import java.util.Collections;

public class PostManagementInputPort implements PostManagementUseCase {

    private final PostManagementOutputPort outputPort;

    public PostManagementInputPort(PostManagementOutputPort outputPort) {
        this.outputPort = outputPort;
    }

    @Override
    public Post create(String title, String content) {
        return new Post(null, title, content, Collections.emptySet());
    }

    @Override
    public Post persist(Post post) {
        return outputPort.persist(post);
    }

    @Override
    public Post retrieve(Long id) {
        return outputPort.retrieve(id);
    }

    @Override
    public Comment addComment(Comment comment) {
        return outputPort.addComment(comment);
    }

    @Override
    public Collection<Comment> listComments(Long id) {
        return outputPort.listComments(id);
    }
}
