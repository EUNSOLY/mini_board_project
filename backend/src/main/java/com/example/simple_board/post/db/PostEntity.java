package com.example.simple_board.post.db;

import com.example.simple_board.board.db.BoardEntity;
import com.example.simple_board.reply.db.ReplyEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DialectOverride;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "post")
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ManyToOne : 나는 N : 상대 1
    // ManyToOne를 사용하면 시스템상으로 변수명 + _id
    // 즉, 이와 같이 표현됨 => board_id
    // JsonIgnore : 상위에서 타고 들어올때 다시 상위로 올라가는 형식은 무한루프가 생성되기 때문에 하위에서는 상위 정보를 보지않겠다
    // @ToString.Exclude : 특정 필드를 toString에서 출력하지 않도록 제외 시킬 때 사용
    @ManyToOne
    @JsonIgnore
    @ToString.Exclude
    private BoardEntity board;

    private String userName;

    private String password;

    private String email;

    private String status;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime postedAt;


    @Transient // 컬럼으로 인지하지않겠다는 의미
    @Builder.Default //Builder는 초기화값을 무시해서 어노테이션을 사용하여 기본값을 유지한다.
    private List<ReplyEntity> replyList = List.of();

}
