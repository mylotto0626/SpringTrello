package com.sparta.springtrello.entity;

import com.sparta.springtrello.domain.board.dto.request.BoardCreateDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "board")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    private String name;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ListEntity> lists = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user", nullable = false)
    private User user;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workspace", nullable = false)
    private Workspace workspace;

    private Board(User user, String name, LocalDateTime createdAt, LocalDateTime modifiedAt){
        this.user = user;
        this.name = name;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }


    public static Board from(User user, BoardCreateDto boardCreateDto) {
        return new Board(
                user,
                boardCreateDto.getName(),
                boardCreateDto.getCreatedAt(),
                boardCreateDto.getModifiedAt()
        );
    }


}
