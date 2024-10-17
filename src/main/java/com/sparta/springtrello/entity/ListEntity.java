package com.sparta.springtrello.entity;

import com.sparta.springtrello.common.entity.Timestamped;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "list")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class ListEntity extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private int listOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")  // 수정: board 관계 설정
    private Board board;

    @Builder
    public ListEntity(String title, int listOrder, Board board) {  // 수정: Board 추가
        this.title = title;
        this.listOrder = listOrder;
        this.board = board;
    }
}
