package com.sparta.springtrello.domain.board.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardUpdateRequest {
    private String name;
    private LocalDateTime modifiedAt;
}
