package com.sparta.springtrello.domain.list.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ListUpdateRequest {
    private String title;
    private LocalDateTime modifiedAt;
}
