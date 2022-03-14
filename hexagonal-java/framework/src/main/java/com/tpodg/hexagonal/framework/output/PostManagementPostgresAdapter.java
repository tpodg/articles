package com.tpodg.hexagonal.framework.output;

import com.tpodg.hexagonal.application.port.output.PostManagementOutputPort;
import com.tpodg.hexagonal.domain.entity.Comment;
import com.tpodg.hexagonal.domain.entity.Post;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@ApplicationScoped
public class PostManagementPostgresAdapter implements PostManagementOutputPort {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public PostManagementPostgresAdapter(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    @Transactional
    public Post persist(Post post) {
        PostEntity entity = new PostEntity();
        entity.title = post.title();
        entity.content = post.content();
        postRepository.persist(entity);
        return new Post(entity.id, entity.title, entity.content, Collections.emptySet());
    }

    @Override
    @Transactional
    public Post retrieve(Long id) {
        PostEntity entity = postRepository.findById(id);
        return new Post(entity.id, entity.title, entity.content, Collections.emptySet());
    }

    @Override
    @Transactional
    public Comment addComment(Comment comment) {
        PostEntity post = postRepository.findByIdOptional(comment.postId()).orElseThrow(() ->
                new EntityNotFoundException("Post not found, id: " + comment.postId()));
        CommentEntity entity = new CommentEntity();
        entity.content = comment.content();
        entity.post = post;
        commentRepository.persist(entity);
        return new Comment(entity.id, entity.content, entity.post.id);
    }

    @Override
    @Transactional
    public Collection<Comment> listComments(Long id) {
        PostEntity post = postRepository.findByIdOptional(id).orElseThrow(() ->
                new EntityNotFoundException("Post not found, id: " + id));
        return post.getComments().stream()
                .map(comment -> new Comment(comment.id, comment.content, comment.post.id))
                .collect(Collectors.toSet());
    }
}
