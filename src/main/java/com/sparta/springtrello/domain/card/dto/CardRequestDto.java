package com.sparta.springtrello.domain.card.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class CardRequestDto {

    private String title;
    private String contents;
    private String dueDate;

    @Builder
    public CardRequestDto(String title, String contents, String dueDate) {
        this.title = title;
        this.contents = contents;
        this.dueDate = dueDate;
    }

}
