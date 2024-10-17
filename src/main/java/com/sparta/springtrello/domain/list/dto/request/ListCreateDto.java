package com.sparta.springtrello.domain.list.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ListCreateDto {

    @NotNull(message = "리스트 이름을 필수로 입력해주세요.")
    private String title;

    @NotNull
    private int listOrder;

    @NotNull  // 수정: boardId 필드 추가
    private Long boardId;

    @Builder
    public ListCreateDto(String title, int listOrder, Long boardId) {
        this.title = title;
        this.listOrder = listOrder;
        this.boardId = boardId;
    }
}
