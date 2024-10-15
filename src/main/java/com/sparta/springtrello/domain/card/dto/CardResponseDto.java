package com.sparta.springtrello.domain.card.dto;

import com.sparta.springtrello.domain.card.entity.Card;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardResponseDto {

    private String title;
    private String contents;
    private String dueDate;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public CardResponseDto(Card card) {
        this.title = card.getTitle();
        this.contents = card.getContents();
        this.dueDate = card.getDueDate();
        this.createdAt = card.getCreatedAt();
        this.modifiedAt = card.getModifiedAt();
    }

}
