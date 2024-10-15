package com.sparta.springtrello.domain.board.dto.response;

import com.sparta.springtrello.entity.Board;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BoardResponse {

    private Long boardId;
    private String name;
    private LocalDateTime createdAt;

    public static BoardResponse from(Board board) {
        return new BoardResponse(
                board.getId(),
                board.getName(),
                board.getCreatedAt()
        );
    }
}

