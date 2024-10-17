package com.sparta.springtrello.domain.list.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ListCreateDto {
    @NotNull(message = "리스트 이름을 필수로 입력해주세요.")
    private String title;

    @NotNull
    private int listOrder;

    @NotNull
    private LocalDateTime createdAt;

    @NotNull
    private LocalDateTime modifiedAt;
}
