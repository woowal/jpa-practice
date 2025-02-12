package com.example.jpa.comment.dto.response;

import com.example.jpa.comment.domain.Comment;
import lombok.Getter;

@Getter
public class CommentResponse {

    private Long id;
    private String content;

    public CommentResponse(Long id, String content) {
        this.id = id;
        this.content = content;
    }

    public static CommentResponse from(Comment comment) {
        return new CommentResponse(comment.getId(), comment.getContent());
    }
}
