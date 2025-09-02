package com.example.simple_board.post.service;

import  com.example.simple_board.board.db.BoardRepository;
import com.example.simple_board.common.Api;
import com.example.simple_board.common.Pagination;
import com.example.simple_board.post.db.PostEntity;
import com.example.simple_board.post.db.PostRepository;
import com.example.simple_board.post.model.PostDto;
import com.example.simple_board.post.model.PostRequest;
import com.example.simple_board.post.model.PostViewRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final BoardRepository boardRepository;
    private final PostConveter postConveter;

    public PostEntity create(
            PostRequest postRequest
    ) {
        var boardEntity = boardRepository.findById(postRequest.getBoardId()).get();// 임시 고정 :
        var entity = PostEntity.builder()
                .board(boardEntity)
                .userName(postRequest.getUserName())
                .password(postRequest.getPassword())
                .email(postRequest.getEmail())
                .status("REGISTERED")
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .postedAt(LocalDateTime.now())
                .build();

        return postRepository.save(entity);
    }

    /**
     * 1. 게시글이 있는가
     * 2. 비밀번호가 맞는가
     *
     * @param postViewRequest
     * @return
     */
    public PostDto view(@Valid PostViewRequest postViewRequest) {
        return  postRepository.findFirstByIdAndStatusOrderByIdDesc(postViewRequest.getPostId(), "REGISTERED")
                .map(it -> {
                    // entity 존재할 때
                    if (!it.getPassword().equals(postViewRequest.getPassword())) {
                        var format = "패스워드가 맞지 않습니다. %s vs %s";

                        throw new RuntimeException(String.format(format, it.getPassword(), postViewRequest.getPassword()));
                    }
                    return postConveter.toDto(it);
                }).orElseThrow(() -> {
                    return new RuntimeException("해당 게시글이 존재 하지 않습니다 :" + postViewRequest.getPostId());
                });

    }

    public Api<List<PostEntity>> all(Pageable pageable) {
        var list = postRepository.findAll(pageable);

        var pagination = Pagination.builder()
                .page(list.getNumber())
                .size(list.getSize())
                .currentElements(list.getNumberOfElements())
                .totalElements(list.getTotalElements())
                .totalPage(list.getTotalPages())
                .build();

        return Api.<List<PostEntity>>builder()
                .body(list.stream().toList())
                .pagination(pagination)
                .build();
    }

    public void delete(@Valid PostViewRequest postViewRequest) {
        postRepository.findById(postViewRequest.getPostId())
                .map(it -> {
                    // entity 존재할 때
                    if (!it.getPassword().equals(postViewRequest.getPassword())) {
                        var format = "패스워드가 맞지 않습니다. %s vs %s";
                        throw new RuntimeException(String.format(format, it.getPassword(), postViewRequest.getPassword()));
                    }

                    it.setStatus("UNREGISTERED");
                    postRepository.save(it);
                    return it;

                }).orElseThrow(() -> {
                    return new RuntimeException("해당 게시글이 존재 하지 않습니다 :" + postViewRequest.getPostId());
                });
    }
}
