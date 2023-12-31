package com.example.jpa.post.service;

import com.example.jpa.comment.domain.Comment;
import com.example.jpa.comment.dto.request.CommentCreateRequest;
import com.example.jpa.comment.dto.response.CommentResponse;
import com.example.jpa.member.domain.Member;
import com.example.jpa.post.domain.Post;
import com.example.jpa.post.dto.request.PostCreateRequest;
import com.example.jpa.post.dto.request.PostUpdateRequest;
import com.example.jpa.post.dto.response.PostResponse;
import com.example.jpa.repository.MemberRepository;
import com.example.jpa.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void create(PostCreateRequest postCreateRequest) {
        Member member = memberRepository.findById(postCreateRequest.getMember()).get();
        postRepository.save(postCreateRequest.toEntity(member));
    }

    @Transactional(readOnly = true)
    public PostResponse findOne(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("게시글이 존재하지 않습니다."));

        return PostResponse.from(post);
    }

    @Transactional
    public void update(Long id, PostUpdateRequest postUpdateRequest) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("게시글이 존재하지 않습니다."));
        post.update(postUpdateRequest.getTitle(), postUpdateRequest.getContent());
    }
}
