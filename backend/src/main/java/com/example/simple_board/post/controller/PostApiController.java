package com.example.simple_board.post.controller;

import com.example.simple_board.post.db.PostEntity;
import com.example.simple_board.post.model.PostDto;
import com.example.simple_board.post.model.PostRequest;
import com.example.simple_board.post.model.PostViewRequest;
import com.example.simple_board.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostApiController {
    private final PostService postService;

    @PostMapping("")
    public PostEntity create(
            @Valid
            @RequestBody
            PostRequest postRequest
    ){
        return  postService.create(postRequest);
    }


    // 게시글 보기
    @PostMapping("view")
    public PostDto view(
            @Valid
            @RequestBody
            PostViewRequest postViewRequest
    ){
        return postService.view(postViewRequest);

    }

    // 리스트
    @GetMapping("/all")
    public List<PostEntity> list(){
        return postService.all();
    }

    // 게시글 삭제
    @PostMapping("/delete")
    public void delete(
            @Valid
            @RequestBody
            PostViewRequest postViewRequest
    ){
        postService.delete(postViewRequest);
    }
}
