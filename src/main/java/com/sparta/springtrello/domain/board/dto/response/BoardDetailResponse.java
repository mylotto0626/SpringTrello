package com.sparta.springtrello.domain.board.dto.response;

import com.sparta.springtrello.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BoardDetailResponse {
    private Long boardId;
    private String name;

    public static BoardDetailResponse from(Board board){
        return new BoardDetailResponse(
                board.getId(),
                board.getName()
        );
    }
}
