package com.sparta.springtrello.domain.board.dto.response;

import com.sparta.springtrello.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BoardUpdateResponse {

    private Long boardId;
    private String name;

    public static BoardUpdateResponse from(Board board) {
        return new BoardUpdateResponse(
                board.getId(),
                board.getName()
        );
    }
}