package com.sparta.springtrello.domain.comment.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class addCommentRequestDto {
    private String content;
    private String emoji;
}
