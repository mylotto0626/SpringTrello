package com.sparta.springtrello.domain.list.dto.response;

import com.sparta.springtrello.entity.ListEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ListDetailResponse {
    private Long listId;
    private String title;
    private int listOrder;
    private LocalDateTime modifiedAt;


    public static ListDetailResponse from(ListEntity list){
        return new ListDetailResponse(
                list.getId(),
                list.getTitle(),
                list.getListOrder(),
                list.getModifiedAt()
        );
    }
}