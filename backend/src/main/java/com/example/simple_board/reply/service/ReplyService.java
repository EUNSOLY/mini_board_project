package com.example.simple_board.reply.service;


import com.example.simple_board.crud.CRUDAbstractService;
import com.example.simple_board.post.db.PostEntity;
import com.example.simple_board.post.db.PostRepository;
import com.example.simple_board.reply.db.ReplyEntity;
import com.example.simple_board.reply.db.ReplyRepository;
import com.example.simple_board.reply.model.ReplyDto;
import com.example.simple_board.reply.model.ReplyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor

public class ReplyService extends CRUDAbstractService<ReplyDto,ReplyEntity> {
}


//public class ReplyService {
//    private final ReplyRepository replyRepository;
//    private final PostRepository postRepository;
//
//    public ReplyEntity create(
//            ReplyRequest replyRequest
//    ){
//
//        var potionalPostEntity = postRepository.findById(replyRequest.getPostId());
//
//        // 값이 없을 수도 있으니 예외 적용해주기
//        if(potionalPostEntity.isEmpty()){
//            throw new RuntimeException("게시물이 존재하지 않습니다." + replyRequest.getPostId());
//        }
//        var entity = ReplyEntity.builder()
//                .post(potionalPostEntity.get())
//                .userName(replyRequest.getUserName())
//                .password(replyRequest.getPassword())
//                .status("REGISTERED")
//                .title(replyRequest.getTitle())
//                .content(replyRequest.getContent())
//                .repliedAt(LocalDateTime.now())
//                .build();
//        return replyRepository.save(entity);
//
//    }
//
//
//    public List<ReplyEntity> findAllByPostId(Long postId){
//        return replyRepository.findAllByPostIdAndStatusOrderByIdDesc(postId,"REGISTERED");
//    }
//
//
//}
