package com.example.simple_board.board.service;

import com.example.simple_board.board.db.BoardEntity;
import com.example.simple_board.board.model.BoardDto;
import com.example.simple_board.post.model.PostDto;
import com.example.simple_board.post.service.PostConveter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

// 데이터를 변환해주는 역활
@Service
@RequiredArgsConstructor
public class BoardConverter {
    private final PostConveter postConveter;

    public BoardDto toDto(BoardEntity boardEntity) {
        var postList = boardEntity.getPostList().stream()
                .map(postConveter::toDto).toList();

        return BoardDto.builder()
                .id(boardEntity.getId())
                .boardName(boardEntity.getBoardName())
                .status(boardEntity.getStatus())
                .postList(postList)
                .build();

    }

}
