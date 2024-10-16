package com.sparta.springtrello.domain.list.dto.response;

import com.sparta.springtrello.domain.board.dto.response.BoardResponse;
import com.sparta.springtrello.entity.Board;
import com.sparta.springtrello.entity.ListEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ListResponse {

    private Long listId;
    private String title;
    private int listOrder;
    private LocalDateTime createdAt;

    public static ListResponse from(ListEntity list) {
        return new ListResponse(
                list.getId(),
                list.getTitle(),
                list.getListOrder(),
                list.getCreatedAt()
        );
    }
}
