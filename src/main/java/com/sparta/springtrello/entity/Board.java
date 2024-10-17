package com.sparta.springtrello.entity;

import com.sparta.springtrello.common.entity.Timestamped;
import com.sparta.springtrello.domain.board.dto.request.BoardCreateDto;
import com.sparta.springtrello.domain.list.dto.request.ListCreateDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;


import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "board")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Board extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    private String name;


    private boolean deletedAt;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ListEntity> lists = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workspace_id", nullable = false)
    private Workspace workspace;

    private Board(User user, String name)  {
        this.user = user;
        this.name = name;
    }


    public static Board from(User user, BoardCreateDto boardCreateDto, Workspace workspace) {
        Board board = new Board(user, boardCreateDto.getName());
        board.setWorkspace(workspace); // Workspace 객체 설정
        return board;
    }

}