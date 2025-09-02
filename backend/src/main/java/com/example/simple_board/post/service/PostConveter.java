package com.example.simple_board.post.service;

import com.example.simple_board.post.db.PostEntity;
import com.example.simple_board.post.model.PostDto;
import com.example.simple_board.reply.model.ReplyDto;
import com.example.simple_board.reply.service.ReplyConveter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
// 데이터를 변환해주는 역활
@Service
@RequiredArgsConstructor
public class PostConveter {
    private final ReplyConveter replyConveter;
    public PostDto toDto(PostEntity postEntity){
        var replyList = postEntity.getReplyList().stream()
                .map(replyConveter::toDto).toList();

        return PostDto.builder()
                .id(postEntity.getId())
                .userName(postEntity.getUserName())
                .status(postEntity.getStatus())
                .email(postEntity.getEmail())
                .password(postEntity.getPassword())
                .title(postEntity.getTitle())
                .content(postEntity.getContent())
                .postedAt(postEntity.getPostedAt())
                .boardId(postEntity.getBoard().getId())
                .replyList(replyList)
                .build();
    }
}
