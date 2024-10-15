package com.sparta.springtrello.entity;

import jakarta.persistence.*;
import lombok.*;

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
    private Date createdAt;
    private Date modifiedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Board board;


    @Builder
    public ListEntity(String title, int listOrder, Date createdAt, Date modifiedAt, Board board) {
        this.title = title;
        this.listOrder = listOrder;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.board = board;
    }
}

