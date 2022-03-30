package com.tpodg.hexagonal.framework.input;

import com.tpodg.hexagonal.application.port.PostManagementService;
import com.tpodg.hexagonal.application.port.output.PostManagementOutputPort;
import com.tpodg.hexagonal.application.port.input.PostManagementUseCase;
import com.tpodg.hexagonal.domain.entity.Comment;
import com.tpodg.hexagonal.domain.entity.Post;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.stream.Collectors;

@Path("/posts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PostManagementRestAdapter {

    private final PostManagementUseCase postManagementUseCase;

    public PostManagementRestAdapter(PostManagementOutputPort postManagementOutputPort) {
        this.postManagementUseCase = new PostManagementService(postManagementOutputPort);
    }

    @GET
    @Path("/{id}")
    public PostDto retrieve(@PathParam("id") Long id) {
        Post retrieved = postManagementUseCase.retrieve(id);
        return new PostDto(retrieved.id(), retrieved.title(), retrieved.content());
    }

    @POST
    public PostDto create(PostDto post) {
        Post created = postManagementUseCase.create(post.title(), post.content());
        return new PostDto(created.id(), created.title(), created.content());
    }

    @POST
    @Path("/{id}/comments")
    public CommentDto comment(@PathParam("id") Long postId, CommentDto comment) {
        Comment created = postManagementUseCase.commentOnPost(postId, comment.content());
        return new CommentDto(created.id(), created.content(), created.postId());
    }

    @GET
    @Path("/{id}/comments")
    public Collection<CommentDto> comments(@PathParam("id") Long postId) {
        Collection<Comment> comments = postManagementUseCase.listPostComments(postId);
        return comments.stream()
                .map(comment -> new CommentDto(comment.id(), comment.content(), comment.postId()))
                .collect(Collectors.toSet());
    }
}