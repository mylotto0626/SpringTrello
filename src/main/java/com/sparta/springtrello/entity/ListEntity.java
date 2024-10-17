package com.sparta.springtrello.entity;

import com.sparta.springtrello.domain.list.dto.request.ListCreateDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "list")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class ListEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private int listOrder;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Board board;


    @Builder
    public ListEntity(String title, int listOrder, LocalDateTime createdAt, LocalDateTime modifiedAt, Board board) {
        this.title = title;
        this.listOrder = listOrder;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.board = board;
    }

    public static ListEntity from(User user, ListCreateDto listCreateDto) {
        return new ListEntity();
    }
}

