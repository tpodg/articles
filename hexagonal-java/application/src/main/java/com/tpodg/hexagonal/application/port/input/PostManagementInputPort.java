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
        Post post = new Post(null, title, content, Collections.emptySet());
        return outputPort.persist(post);
    }

    @Override
    public Post retrieve(Long id) {
        return outputPort.retrieve(id);
    }

    @Override
    public Comment commentOnPost(Long postId, String content) {
        return outputPort.addComment(new Comment(null, content, postId));
    }

    @Override
    public Collection<Comment> listPostComments(Long id) {
        return outputPort.listComments(id);
    }
}
