package com.example.simple_board.board.db;

import com.example.simple_board.post.db.PostEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;


import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity(name = "board")

public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String boardName;

    private String status;

    // 1:N을 의미하며
    // 1 == board : N == PostEntity
    @Builder.Default
    @OneToMany(mappedBy = "board")
//    @Where(clause = "status = 'REGISTERED'")
    @SQLRestriction("status = 'REGISTERED'") // @Where 대신 @SQLRestriction 사용
    @OrderBy("id desc ")
    private List<PostEntity> postList = List.of();

}
