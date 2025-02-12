package com.example.jpa.repository;

import com.example.jpa.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("select new com.example.jpa.comment.domain.Comment(c.member, c.content) " +
            "from Comment c " +
            "where c.post.id = :id")
    List<Comment> findByPostID(@Param("id") Long id);

}
